package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteClientDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteClientListRespDto
 * @Description 线路团的出游人数据
 * @Author Jm.zhang
 * @Date 2019/12/24 19:15
 * @Version 1.0
 **/
@ApiModel(description = "线路团的出游人数据响应")
public class RouteClientListRespDto {

    private List<RouteClientDto> route_client;

    public List<RouteClientDto> getRoute_client() {
        return route_client;
    }

    public void setRoute_client(List<RouteClientDto> route_client) {
        this.route_client = route_client;
    }
}
