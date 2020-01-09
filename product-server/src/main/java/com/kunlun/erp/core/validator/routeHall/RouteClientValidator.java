package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.routeHall.request.RouteClientListRequest;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RouteClientValidator
 * @Description 线路出游人验器
 * @Author Jm.zhang
 * @Date 2019/12/24 20:17
 * @Version 1.0
 **/
@Component(value = "route_client_validator")
public class RouteClientValidator  extends AbstractValidator {
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    public RouteClientValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return RouteClientListRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code = null;
        if (obj instanceof  RouteClientListRequest){
            RouteClientListRequest request = (RouteClientListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }


        return error_code;
    }
}
