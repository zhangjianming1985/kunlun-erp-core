package com.kunlun.erp.core.dto.routeHall.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.routeHall.RouteHallDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName HallProductListRespDto
 * @Description 报名大厅产品列表响应
 * @Author Jm.zhang
 * @Date 2019/12/18 21:24
 * @Version 1.0
 **/
@ApiModel(description = "报名大厅产品列表响应")
public class HallProductListRespDto {
    private PageInfo<RouteHallDto> page_data;

    public PageInfo<RouteHallDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<RouteHallDto> page_data) {
        this.page_data = page_data;
    }
}
