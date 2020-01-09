package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteResideDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteResideListRespDto
 * @Description 线路团住宿数据响应
 * @Author Jm.zhang
 * @Date 2019/12/20 16:49
 * @Version 1.0
 **/
@ApiModel(description = "线路团住宿数据响应")
public class RouteResideListRespDto {

    private List<RouteResideDto> reside_data;

    public List<RouteResideDto> getReside_data() {
        return reside_data;
    }

    public void setReside_data(List<RouteResideDto> reside_data) {
        this.reside_data = reside_data;
    }
}
