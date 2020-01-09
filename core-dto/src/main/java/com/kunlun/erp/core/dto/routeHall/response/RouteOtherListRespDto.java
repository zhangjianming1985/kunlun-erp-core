package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteOtherDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteOtherListRespDto
 * @Description 线路团其他响应
 * @Author Jm.zhang
 * @Date 2019-12-23 0:56
 * @Version 1.0
 **/
@ApiModel(description = "线路团其他响应")
public class RouteOtherListRespDto {
    private List<RouteOtherDto> other_data;

    public List<RouteOtherDto> getOther_data() {
        return other_data;
    }

    public void setOther_data(List<RouteOtherDto> other_data) {
        this.other_data = other_data;
    }
}
