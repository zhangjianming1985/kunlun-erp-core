package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteClientDto
 * @Description 线路团关联出游人数据
 * @Author Jm.zhang
 * @Date 2019/12/24 19:08
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联出游人数据")
public class RouteClientDto {

    private SalesChannelDto sales_channel_data;

    private List<OrderClientDto> client_data;

    public SalesChannelDto getSales_channel_data() {
        return sales_channel_data;
    }

    public void setSales_channel_data(SalesChannelDto sales_channel_data) {
        this.sales_channel_data = sales_channel_data;
    }

    public List<OrderClientDto> getClient_data() {
        return client_data;
    }

    public void setClient_data(List<OrderClientDto> client_data) {
        this.client_data = client_data;
    }
}
