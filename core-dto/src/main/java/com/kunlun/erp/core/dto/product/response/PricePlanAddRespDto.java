package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName PricePlanAddRespDto
 * @Description 创建价格方案响应
 * @Author Jm.zhang
 * @Date 2019/12/16 18:39
 * @Version 1.0
 **/
@ApiModel(description = "价格套餐设置响应")
public class PricePlanAddRespDto {
    @ApiModelProperty(value = "价格套餐编号",example = "50000000000")
    private String price_plan_code;

    @ApiModelProperty(value = "价格套餐名称",example = "默认套餐1")
    private  String price_plan_name;

    @ApiModelProperty(value = "线路方案编号",example = "66000000000")
    private String route_plan_code;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }

    public String getPrice_plan_name() {
        return price_plan_name;
    }

    public void setPrice_plan_name(String price_plan_name) {
        this.price_plan_name = price_plan_name;
    }

    public String getRoute_plan_code() {
        return route_plan_code;
    }

    public void setRoute_plan_code(String route_plan_code) {
        this.route_plan_code = route_plan_code;
    }
}
