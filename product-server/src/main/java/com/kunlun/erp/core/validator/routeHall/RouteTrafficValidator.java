package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteTrafficDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficListRequest;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.entity.RouteTraffic;
import com.kunlun.erp.core.mapper.RouteTrafficMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import com.kunlun.erp.core.validator.routeOrder.OrderClientValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteTrafficValidator
 * @Description 线路交通票务校验器
 * @Author Jm.zhang
 * @Date 2019-12-25 0:12
 * @Version 1.0
 **/
@Component(value = "route_traffic_validator")
public class RouteTrafficValidator extends AbstractValidator {
    @Resource
    private RouteTrafficMapper traffic_dao;
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource(name = "order_client_validator")
    private OrderClientValidator order_client_validator;

    public RouteTrafficValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return RouteTrafficListRequest.class.isAssignableFrom(clazz) || RouteTrafficAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteTrafficListRequest){
            RouteTrafficListRequest request = (RouteTrafficListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteTrafficAddRequest){
            RouteTrafficAddRequest request = (RouteTrafficAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                error_code = this.checkTraffic(request.getBody().getTraffic_data());
            }
            if (error_code == null){
                error_code = this.checkClient(request.getBody().getGroup_code(),request.getBody().getClient_data());
            }
        }
        return error_code;
    }

    public String checkClient(String group_code,List<OrderClientDto> client_data){
        String error_code = null;
        if (client_data!=null && client_data.isEmpty()==false){
           for (OrderClientDto dto :client_data){
               if (StringUtils.isBlank(dto.getClient_code()) || order_client_validator.checkClientCode(dto.getClient_code(),group_code)==false){
                   error_code=ErrorCodeConstant.ROUTE_CLIENT_CODE_INVALID;
                   break;
               }
               if (dto.getTicket_state()!=null && SysConstant.ClientTrafficState.getClientTrafficState(dto.getTicket_state())==null){
                   error_code=ErrorCodeConstant.ROUTE_CLIENT_WITH_TRAFFIC_TICKET_STATUS_INVALID;
                   break;
               }
           }

        }
        return error_code;
    }


    public String checkTraffic(List<RouteTrafficDto> traffic_data){
        String error_code = null;
        if (traffic_data!=null && traffic_data.isEmpty()==false){
            for (RouteTrafficDto dto : traffic_data){
                if (StringUtils.isNotBlank(dto.getTraffic_code())){
                    RouteTraffic record = traffic_dao.selectByTrafficCode(dto.getTraffic_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.ROUTE_TRAFFIC_CODE_INVALID;
                        break;
                    }
                }
                if (SysConstant.TrafficType.getTrafficType(dto.getTraffic_type())==null){
                    error_code=ErrorCodeConstant.ROUTE_TRAFFIC_TYPE_INVALID;
                    break;
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_traffic.getValue());
                if (error_code!=null)break;
                if (RegexUtil.isDate(dto.getDeparture_date())==false){
                    error_code = ErrorCodeConstant.ROUTE_TRAFFIC_DEPARTURE_DATE_INVALID;
                    break;
                }
                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.ROUTE_TRAFFIC_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.ROUTE_TRAFFIC_FEE_INVALID;
                    break;
                }
                if (dto.getTraffic_count() <=0){
                    error_code = ErrorCodeConstant.ROUTE_TRAFFIC_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getTraffic_count(),dto.getFee());
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
            return ErrorCodeConstant.ROUTE_TRAFFIC_TOTAL_FEE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.ROUTE_TRAFFIC_TOTAL_FEE_INVALID;
        }
        return  null;
    }
}
