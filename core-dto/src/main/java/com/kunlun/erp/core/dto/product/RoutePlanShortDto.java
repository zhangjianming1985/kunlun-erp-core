package com.kunlun.erp.core.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RoutePlanShortDto
 * @Description 线路计划精简字段
 * @Author Jm.zhang
 * @Date 2019/12/9 20:16
 * @Version 1.0
 **/
@ApiModel(description = "行程计划数据")
public class RoutePlanShortDto {
    @ApiModelProperty(value = "线路计划编号（行程1 行程2 ...）",example = "8000001339037607")
    private String route_plan_code;

    @ApiModelProperty(required = true,value = "线路计划名称",example = "测试默认行程1")
    private String route_plan_name;

    public String getRoute_plan_code() {
        return route_plan_code;
    }

    public void setRoute_plan_code(String route_plan_code) {
        this.route_plan_code = route_plan_code;
    }

    public String getRoute_plan_name() {
        return route_plan_name;
    }

    public void setRoute_plan_name(String route_plan_name) {
        this.route_plan_name = route_plan_name;
    }
}
