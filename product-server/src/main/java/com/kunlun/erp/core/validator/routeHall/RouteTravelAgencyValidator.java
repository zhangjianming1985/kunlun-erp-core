package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyDto;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyIncomeDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteTravelAgency;
import com.kunlun.erp.core.entity.RouteTravelAgencyIncome;
import com.kunlun.erp.core.mapper.RouteTravelAgencyIncomeMapper;
import com.kunlun.erp.core.mapper.RouteTravelAgencyMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RouteTravelAgencyValidator
 * @Description 线路团的地接旅行社校验
 * @Author Jm.zhang
 * @Date 2019/12/25 13:41
 * @Version 1.0
 **/
@Component(value = "route_travelAgency_validator")
public class RouteTravelAgencyValidator extends AbstractValidator {
    @Resource
    private RouteTravelAgencyMapper travel_dao;
    @Resource(name = "person_service")
    private PersonService person_service;
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource
    private RouteTravelAgencyIncomeMapper income_dao;

    public RouteTravelAgencyValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteTravelAgencyListRequest.class.isAssignableFrom(clazz) || RouteTravelAgencyAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteTravelAgencyListRequest){
            RouteTravelAgencyListRequest request = (RouteTravelAgencyListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteTravelAgencyAddRequest){
            RouteTravelAgencyAddRequest request = (RouteTravelAgencyAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code == null){
                error_code = this.checkTravelAgency(request.getBody().getTravel_agency_data());
            }
        }
        return error_code;
    }

    public String checkTravelAgency(List<RouteTravelAgencyDto> travelAgency_data){
        String error_code = null;
        if (travelAgency_data!=null && travelAgency_data.isEmpty()==false){
            for (RouteTravelAgencyDto dto : travelAgency_data){
                if (StringUtils.isNotBlank(dto.getTravel_code())){
                    RouteTravelAgency record = travel_dao.selectByTravelAgencyCode(dto.getTravel_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.ROUTE_TRAVEL_AGENCY_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_travel_agency.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                error_code = this.checkIncome(dto.getIncome_data());

            }
        }
        return error_code;
    }

    public String checkIncome(List<RouteTravelAgencyIncomeDto> income_data){
        String error_code = null;
        if (income_data!=null && income_data.size()>0){
            for (RouteTravelAgencyIncomeDto dto :income_data){
                if (StringUtils.isNotBlank(dto.getIncome_code())){
                    error_code = this.checkIncomeCode(dto.getIncome_code());
                    if (error_code !=null){
                        break;
                    }
                }
                error_code = route_plan_price_validator.checkFeeType(dto.getFee_type());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_TRAVEL_INCOME_FEE_TYPE_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkCurrency(dto.getCurrency());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_TRAVEL_INCOME_CURRENCY_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkPrice(dto.getPrice());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_TRAVEL_INCOME_PRICE_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkQuantity(dto.getQuantity());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_TRAVEL_INCOME_SIZE_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkTotalPrice(dto.getTotal_price(),dto.getQuantity(),dto.getPrice());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_TRAVEL_INCOME_TOTAL_PRICE_INVALID;
                    break;
                }
            }
        }
        return error_code;

    }

    /**
     * 校验地接款编号
     * @param income_code
     * @return
     */
    public String checkIncomeCode(String income_code){
        RouteTravelAgencyIncome record = income_dao.selectByIncomeCode(income_code);
        if (record == null)return ErrorCodeConstant.ROUTE_TRAVEL_INCOME_CODE_INVALID;
        return null;
    }


}
