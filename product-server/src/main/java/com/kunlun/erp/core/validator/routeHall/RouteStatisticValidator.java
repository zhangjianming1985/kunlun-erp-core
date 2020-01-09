package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.routeHall.request.RouteStatisticsRequest;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RouteStatisticValidator
 * @Description 线路收支统计校验器
 * @Author Jm.zhang
 * @Date 2019/12/26 10:19
 * @Version 1.0
 **/
@Component(value = "route_statistic_validator")
public class RouteStatisticValidator  extends AbstractValidator {
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    public RouteStatisticValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteStatisticsRequest.class.isAssignableFrom(clazz);
    }
    @Override
    public String myValidate(Object obj) {
        String error_code = null;
        if (obj instanceof  RouteStatisticsRequest){
            RouteStatisticsRequest request = (RouteStatisticsRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }
        return error_code;
    }

}
