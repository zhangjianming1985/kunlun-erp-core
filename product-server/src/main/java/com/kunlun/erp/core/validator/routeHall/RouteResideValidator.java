package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteResideDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteReside;
import com.kunlun.erp.core.mapper.RouteResideMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteResideValidator
 * @Description 线路住宿校验器
 * @Author Jm.zhang
 * @Date 2019/12/20 17:20
 * @Version 1.0
 **/
@Component(value = "route_reside_validator")
public class RouteResideValidator extends AbstractValidator {
    public RouteResideValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }

    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource
    private RouteResideMapper reside_dao;
    @Resource(name = "person_service")
    private PersonService person_service;


    @Override
    public boolean supports(Class<?> clazz) {
        return RouteResideListRequest.class.isAssignableFrom(clazz) || RouteResideAddRequest.class.isAssignableFrom(clazz);
    }
    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteResideListRequest){
            RouteResideListRequest request = (RouteResideListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteResideAddRequest){
            RouteResideAddRequest request = (RouteResideAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                error_code = this.checkReside(request.getBody().getReside_data());
            }
        }
        return error_code;
    }

    public String checkReside(List<RouteResideDto> reside_data){
        String error_code = null;
        if (reside_data!=null && reside_data.isEmpty()==false){
            for (RouteResideDto dto : reside_data){
                if (StringUtils.isNotBlank(dto.getReside_code())){
                    RouteReside record = reside_dao.selectByResideCode(dto.getReside_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.RESIDE_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_hotel.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (SysConstant.RoomType.getRoomType(dto.getRoom_type())==null){
                    error_code = ErrorCodeConstant.RESIDE_ROOM_TYPE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getStart_date())==false){
                    error_code = ErrorCodeConstant.RESIDE_START_DATE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getEnd_date())==false){
                    error_code = ErrorCodeConstant.RESIDE_END_DATE_INVALID;
                    break;
                }
                if (dto.getDays() <=0){
                    error_code =ErrorCodeConstant.RESIDE_DAYS_INVALID;
                    break;
                }
                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.RESIDE_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.RESIDE_FEE_INVALID;
                    break;
                }
                if (dto.getRoom_count() <=0){
                    error_code = ErrorCodeConstant.RESIDE_ROOM_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getDays(),dto.getFee(),dto.getRoom_count());
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
    public String checkTotalPrice(String  total_price,Integer days,String price,Integer room_count){
        if (RegexUtil.isDecimal(total_price)==false){
            return ErrorCodeConstant.RESIDE_TOTAL_FEE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*days*room_count)){
            return ErrorCodeConstant.RESIDE_TOTAL_FEE_INVALID;
        }

        return  null;
    }
}
