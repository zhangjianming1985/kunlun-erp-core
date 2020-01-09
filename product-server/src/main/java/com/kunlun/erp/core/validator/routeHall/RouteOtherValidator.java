package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteOtherDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteOther;
import com.kunlun.erp.core.mapper.RouteOtherMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteOtherValidator
 * @Description 线路其他校验器
 * @Author Jm.zhang
 * @Date 2019-12-23 0:57
 * @Version 1.0
 **/
@Component(value = "route_other_validator")
public class RouteOtherValidator extends AbstractValidator {
    public RouteOtherValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource
    private RouteOtherMapper other_dao;
    @Resource(name = "person_service")
    private PersonService person_service;

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteOtherListRequest.class.isAssignableFrom(clazz) || RouteOtherAddRequest.class.isAssignableFrom(clazz);
    }
    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteOtherListRequest){
            RouteOtherListRequest request = (RouteOtherListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteOtherAddRequest){
            RouteOtherAddRequest request = (RouteOtherAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code == null){
                error_code = this.checkOther(request.getBody().getOther_data());
            }
        }
        return error_code;
    }

    public String checkOther(List<RouteOtherDto> other_data){
        String error_code = null;
        if (other_data!=null && other_data.isEmpty()==false){
            for (RouteOtherDto dto : other_data){
                if (StringUtils.isNotBlank(dto.getOther_code())){
                    RouteOther record = other_dao.selectByOtherCode(dto.getOther_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.OTHER_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_other.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.OTHER_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.OTHER_PRICE_INVALID;
                    break;
                }
                if (dto.getQuantity() <=0){
                    error_code = ErrorCodeConstant.OTHER_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getQuantity(),dto.getFee());
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
            return ErrorCodeConstant.OTHER_TOTAL_PRICE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.OTHER_TOTAL_PRICE_INVALID;
        }
        return  null;
    }
}
