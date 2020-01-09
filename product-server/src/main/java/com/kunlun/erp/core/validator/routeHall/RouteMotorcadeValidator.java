package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteMotorcadeDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteMotorcade;
import com.kunlun.erp.core.mapper.RouteMotorcadeMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteMotorcadeValidator
 * @Description 线路车队校验器
 * @Author Jm.zhang
 * @Date 2019-12-22 22:49
 * @Version 1.0
 **/
@Component(value = "route_motorcade_validator")
public class RouteMotorcadeValidator  extends AbstractValidator {
    public RouteMotorcadeValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;

    @Resource
    private RouteMotorcadeMapper motorcade_dao;
    @Resource(name = "person_service")
    private PersonService person_service;
    @Override
    public boolean supports(Class<?> clazz) {
        return RouteMotorcadeListRequest.class.isAssignableFrom(clazz) || RouteMotorcadeAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteMotorcadeListRequest){
            RouteMotorcadeListRequest request = (RouteMotorcadeListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteMotorcadeAddRequest){
            RouteMotorcadeAddRequest request = (RouteMotorcadeAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code == null){
                error_code = this.checkMotorcade(request.getBody().getMotorcade_data());
            }
        }
        return error_code;
    }


    public String checkMotorcade(List<RouteMotorcadeDto> motorcade_data){
        String error_code = null;
        if (motorcade_data!=null && motorcade_data.isEmpty()==false){
            for (RouteMotorcadeDto dto : motorcade_data){
                if (StringUtils.isNotBlank(dto.getMotorcade_code())){
                    RouteMotorcade record = motorcade_dao.selectByMotorcadeCode(dto.getMotorcade_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.MOTORCADE_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_car.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getStart_date())==false){
                    error_code = ErrorCodeConstant.MOTORCADE_START_DATE_INVALID;
                    break;
                }

                if (RegexUtil.isDate(dto.getEnd_date())==false){
                    error_code = ErrorCodeConstant.MOTORCADE_END_DATE_INVALID;
                    break;
                }
                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.MOTORCADE_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.MOTORCADE_PRICE_INVALID;
                    break;
                }
                if (dto.getCar_count() <=0){
                    error_code = ErrorCodeConstant.MOTORCADE_CAR_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getCar_count(),dto.getFee());
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
            return ErrorCodeConstant.MOTORCADE_TOTAL_PRICE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.MOTORCADE_TOTAL_PRICE_INVALID;
        }
        return  null;
    }
}
