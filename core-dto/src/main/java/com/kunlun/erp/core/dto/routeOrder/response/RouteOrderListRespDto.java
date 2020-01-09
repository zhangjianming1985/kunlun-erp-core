package com.kunlun.erp.core.dto.routeOrder.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.routeOrder.OrderListDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName RouteOrderListRespDto
 * @Description 线路订单列表响应
 * @Author Jm.zhang
 * @Date 2019-12-24 0:00
 * @Version 1.0
 **/
@ApiModel(description = "RouteOrderListRespDto")
public class RouteOrderListRespDto {

    private PageInfo<OrderListDto> page_data;

    public PageInfo<OrderListDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<OrderListDto> page_data) {
        this.page_data = page_data;
    }
}
