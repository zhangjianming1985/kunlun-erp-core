package com.kunlun.erp.core.dto.routeOrder.response;

import com.kunlun.erp.core.dto.product.RouteProductDto;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.dto.routeOrder.RouteOrderBaseInfoDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteOrderDetailRespDto
 * @Description 线路订单详情数据
 * @Author Jm.zhang
 * @Date 2019/12/23 11:37
 * @Version 1.0
 **/
@ApiModel(description = "线路订单详情数据")
public class RouteOrderDetailRespDto {
    /**
     * 订单数据
     */
    private RouteOrderBaseInfoDto order_data;

    /**
     * 团款
     */
    private List<OrderIncomeDto> income_data;

    /**
     * 产品数据
     */
    private RouteProductDto product_data;

    public RouteOrderBaseInfoDto getOrder_data() {
        return order_data;
    }

    public void setOrder_data(RouteOrderBaseInfoDto order_data) {
        this.order_data = order_data;
    }

    public RouteProductDto getProduct_data() {
        return product_data;
    }

    public void setProduct_data(RouteProductDto product_data) {
        this.product_data = product_data;
    }

    public List<OrderIncomeDto> getIncome_data() {
        return income_data;
    }

    public void setIncome_data(List<OrderIncomeDto> income_data) {
        this.income_data = income_data;
    }
}
