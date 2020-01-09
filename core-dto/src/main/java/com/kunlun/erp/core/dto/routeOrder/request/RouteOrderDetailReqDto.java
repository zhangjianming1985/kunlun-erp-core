package com.kunlun.erp.core.dto.routeOrder.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOrderDetailReqDto
 * @Description 获取线路订单详情
 * @Author Jm.zhang
 * @Date 2019/12/23 11:59
 * @Version 1.0
 **/
@ApiModel(description = "获取线路订单详情")
public class RouteOrderDetailReqDto {
    @ApiModelProperty(required = true,value = "订单编号",example = "123")
    private String order_code;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
}
