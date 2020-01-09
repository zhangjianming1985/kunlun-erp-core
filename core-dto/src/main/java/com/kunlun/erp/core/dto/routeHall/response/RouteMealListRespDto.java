package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteMealDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteMealListRespDto
 * @Description 线路团用餐据响应
 * @Author Jm.zhang
 * @Date 2019-12-22 18:40
 * @Version 1.0
 **/
@ApiModel(description = "线路团用餐据响应")
public class RouteMealListRespDto {

    private List<RouteMealDto> meal_data;

    public List<RouteMealDto> getMeal_data() {
        return meal_data;
    }

    public void setMeal_data(List<RouteMealDto> meal_data) {
        this.meal_data = meal_data;
    }
}
