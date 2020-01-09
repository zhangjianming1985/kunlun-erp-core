package com.kunlun.erp.core.dto.routeOrder.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOrderDeleteRespDto
 * @Description 删除团的订单结果
 * @Author Jm.zhang
 * @Date 2019-12-31 0:34
 * @Version 1.0
 **/
@ApiModel(description = "删除团的订单结果")
public class RouteOrderDeleteRespDto {
    @ApiModelProperty(value = "删除的出游人数量",example = "10")
    public int client_count;

    @ApiModelProperty(value = "删除的团款数据数量",example = "10")
    public int income_count;

    @ApiModelProperty(value = "删除的订单数量",example = "1")
    public int order_count;

    public int getClient_count() {
        return client_count;
    }

    public void setClient_count(int client_count) {
        this.client_count = client_count;
    }

    public int getIncome_count() {
        return income_count;
    }

    public void setIncome_count(int income_count) {
        this.income_count = income_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }
}
