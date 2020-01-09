package com.kunlun.erp.core.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName PricePlanDto
 * @Description 价格套餐数据
 * @Author Jm.zhang
 * @Date 2019-12-17 1:00
 * @Version 1.0
 **/
@ApiModel(description = "价格套餐数据")
public class PricePlanDto {
    @ApiModelProperty(value = "价格套餐编号",example = "11111111111")
    private String price_plan_code;

    @ApiModelProperty(value = "产品编号",example = "222222222222")
    private String product_code;

    @ApiModelProperty(value = "关联线路方案编号",example = "33333333333333333")
    private String route_plan_code;

    @ApiModelProperty(value = "价格套餐名称",example = "我的套餐1")
    private String price_plan_name;

    private List<RoutePricePlanDetailDto> price_data;

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

    public List<RoutePricePlanDetailDto> getPrice_data() {
        return price_data;
    }

    public void setPrice_data(List<RoutePricePlanDetailDto> price_data) {
        this.price_data = price_data;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getRoute_plan_code() {
        return route_plan_code;
    }

    public void setRoute_plan_code(String route_plan_code) {
        this.route_plan_code = route_plan_code;
    }
}
