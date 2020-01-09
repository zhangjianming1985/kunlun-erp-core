package com.kunlun.erp.core.dto.routeOrder.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOrderAddRespDto
 * @Description 创建编辑线路订单响应
 * @Author Jm.zhang
 * @Date 2019/12/23 16:18
 * @Version 1.0
 **/
@ApiModel(description = "创建编辑线路订单响应")
public class RouteOrderAddRespDto {
    @ApiModelProperty(value = "订单编号",example = "8888886668884")
    private String order_code;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
}
