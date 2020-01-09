package com.kunlun.erp.core.validator.routeOrder;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.dto.routeOrder.request.ClientAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.ClientListRequest;
import com.kunlun.erp.core.entity.OrderClient;
import com.kunlun.erp.core.entity.RouteHall;
import com.kunlun.erp.core.entity.RouteOrder;
import com.kunlun.erp.core.mapper.OrderClientMapper;
import com.kunlun.erp.core.mapper.RouteHallMapper;
import com.kunlun.erp.core.mapper.RouteOrderMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName OrderClientValidator
 * @Description 订单出游人校验器
 * @Author Jm.zhang
 * @Date 2019/12/24 14:36
 * @Version 1.0
 **/
@Component(value = "order_client_validator")
public class OrderClientValidator  extends AbstractValidator {
    public OrderClientValidator(){
        super.name_space= Urls.RouteOrder.NAMESPACE;
    }

    @Resource(name = "route_order_validator")
    private RouteOrderValidator route_order_validator;
    @Resource
    private RouteOrderMapper order_dao;
    @Resource
    private OrderClientMapper client_dao;
    @Resource
    private RouteHallMapper hall_dao;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientAddRequest.class.isAssignableFrom(clazz) || ClientListRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        return null;
    }

    @Override
    public String myValidate(Object obj) {
        String error_code= null;
        if (obj instanceof ClientListRequest){
            ClientListRequest request = (ClientListRequest)obj;
            error_code = route_order_validator.checkOrderCode(request.getBody().getOrder_code());
        }else if (obj instanceof ClientAddRequest){
            ClientAddRequest  request = (ClientAddRequest)obj;
            error_code = route_order_validator.checkOrderCode(request.getBody().getOrder_code());
            if (error_code == null){
                RouteOrder order_record = order_dao.selectByOrderCode(request.getBody().getOrder_code());
                RouteHall record= hall_dao.selectByGroupCode(order_record.getGroup_code());
                if (record.getApprove_state()== SysConstant.FinanceAuditStatus.approved.getValue()){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_NO_EDIT;
                }
            }

            if (error_code == null){
                if (request.getBody().getClient_data()!= null && request.getBody().getClient_data().isEmpty()==false){
                    //出游人数量 不能大于基本信息的设定
                    RouteOrder order_record = order_dao.selectByOrderCode(request.getBody().getOrder_code());
                    int total = order_record.getAdult_count()+order_record.getChildren_count();
                    if (request.getBody().getClient_data().size() > total){
                        error_code= ErrorCodeConstant.ROUTE_CLIENT_GREATER_THAN_SETTING;
                    }
                    if (error_code == null){
                        for (OrderClientDto dto : request.getBody().getClient_data()){
                            if (StringUtils.isNotBlank(dto.getClient_code())){
                                if (client_dao.selectByClientCodeAndOrderCode(dto.getClient_code(),request.getBody().getOrder_code())==null){
                                    error_code= ErrorCodeConstant.ROUTE_CLIENT_CODE_INVALID;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return error_code;
    }

    public boolean checkClientCode(String client_code){
        OrderClient record = client_dao.selectByClientCode(client_code);
        return record != null;
    }

    public boolean checkClientCode(String client_code,String group_code){
        OrderClient record = client_dao.selectByClientCodeAndGroupCode(client_code,group_code);
        return record != null;
    }
}
