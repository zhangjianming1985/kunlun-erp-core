package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteInsuranceDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteInsurance;
import com.kunlun.erp.core.mapper.RouteInsuranceMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteInsuranceValidator
 * @Description 线路保险服务校验器
 * @Author Jm.zhang
 * @Date 2019-12-22 23:56
 * @Version 1.0
 **/
@Component(value = "route_insurance_validator")
public class RouteInsuranceValidator extends AbstractValidator {
    public RouteInsuranceValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Resource
    private RouteInsuranceMapper insurance_dao;
    @Resource(name = "person_service")
    private PersonService person_service;
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;


    @Override
    public boolean supports(Class<?> clazz) {
        return RouteInsuranceListRequest.class.isAssignableFrom(clazz) || RouteInsuranceAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteInsuranceListRequest){
            RouteInsuranceListRequest request = (RouteInsuranceListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteInsuranceAddRequest){
            RouteInsuranceAddRequest request = (RouteInsuranceAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code == null){
                error_code = this.checInsurance(request.getBody().getInsurance_data());
            }
        }
        return error_code;
    }



    public String checInsurance(List<RouteInsuranceDto> insurance_data){
        String error_code = null;
        if (insurance_data!=null && insurance_data.isEmpty()==false){
            for (RouteInsuranceDto dto : insurance_data){
                if (StringUtils.isNotBlank(dto.getInsurance_code())){
                    RouteInsurance record = insurance_dao.selectByInsuranceCode(dto.getInsurance_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.INSURANCE_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_insurance.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (SysConstant.InsuranceType.getInsuranceType(dto.getInsurance_type())== null){
                    error_code = ErrorCodeConstant.INSURANCE_TYPE_INVALID;
                    break;
                }

                if (RegexUtil.isDate(dto.getStart_date())==false){
                    error_code = ErrorCodeConstant.INSURANCE_START_DATE_INVALID;
                    break;
                }

                if (RegexUtil.isDate(dto.getEnd_date())==false){
                    error_code = ErrorCodeConstant.INSURANCE_END_DATE_INVALID;
                    break;
                }
                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.INSURANCE_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.INSURANCE_PRICE_INVALID;
                    break;
                }
                if (dto.getInsurance_count() <=0){
                    error_code = ErrorCodeConstant.INSURANCE_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getInsurance_count(),dto.getFee());
                if (error_code!=null)break;
            }
        }
        return error_code;
    }
    /**
     * 总价
     * @param total_price
     * @return
     */
    public String checkTotalPrice(String  total_price,Integer quantity,String price){
        if (RegexUtil.isDecimal(total_price)==false){
            return ErrorCodeConstant.INSURANCE_TOTAL_PRICE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.INSURANCE_TOTAL_PRICE_INVALID;
        }
        return  null;
    }

}
