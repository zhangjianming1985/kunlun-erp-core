package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteGuidesDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteGuideListRespDto
 * @Description 线路团的导服数据响应
 * @Author Jm.zhang
 * @Date 2019/12/19 19:13
 * @Version 1.0
 **/
@ApiModel(description = "线路团的导服数据响应")
public class RouteGuideListRespDto {

    private List<RouteGuidesDto> guides_data;

    public List<RouteGuidesDto> getGuides_data() {
        return guides_data;
    }

    public void setGuides_data(List<RouteGuidesDto> guides_data) {
        this.guides_data = guides_data;
    }
}
