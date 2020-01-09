package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteTrafficDto;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteTrafficListRespDto
 * @Description 线路交通票务数据响应
 * @Author Jm.zhang
 * @Date 2019-12-24 23:49
 * @Version 1.0
 **/

@ApiModel(description = "线路交通票务数据响应")
public class RouteTrafficListRespDto {
    private List<RouteTrafficDto> traffic_data;

    private List<OrderClientDto> client_data;

    public List<RouteTrafficDto> getTraffic_data() {
        return traffic_data;
    }

    public void setTraffic_data(List<RouteTrafficDto> traffic_data) {
        this.traffic_data = traffic_data;
    }

    public List<OrderClientDto> getClient_data() {
        return client_data;
    }

    public void setClient_data(List<OrderClientDto> client_data) {
        this.client_data = client_data;
    }
}
