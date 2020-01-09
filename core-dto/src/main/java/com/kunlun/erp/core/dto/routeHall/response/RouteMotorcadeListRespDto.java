package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteMotorcadeDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteMotorcadeListRespDto
 * @Description 线路团车队响应
 * @Author Jm.zhang
 * @Date 2019-12-22 22:31
 * @Version 1.0
 **/
@ApiModel(description = "线路团车队响应")
public class RouteMotorcadeListRespDto {
    private List<RouteMotorcadeDto> motorcade_data;

    public List<RouteMotorcadeDto> getMotorcade_data() {
        return motorcade_data;
    }

    public void setMotorcade_data(List<RouteMotorcadeDto> motorcade_data) {
        this.motorcade_data = motorcade_data;
    }
}
