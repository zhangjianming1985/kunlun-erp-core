package com.kunlun.erp.core.dto.routeOrder.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOrderDeleteReqDto
 * @Description 删除线路团订单数据
 * @Author Jm.zhang
 * @Date 2019-12-31 0:32
 * @Version 1.0
 **/
@ApiModel(description = "删除线路团订单数据")
public class RouteOrderDeleteReqDto {
    @ApiModelProperty(required = true,value = "订单编号",example = "123")
    private String order_code;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
}
