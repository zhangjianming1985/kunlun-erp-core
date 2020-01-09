package com.kunlun.erp.core.dto.finance.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.finance.RouteHallEndDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName HallProductEndListRespDto
 * @Description 发团审核数据响应
 * @Author Jm.zhang
 * @Date 2019-12-27 1:46
 * @Version 1.0
 **/
@ApiModel(description = "发团审核数据响应")
public class HallProductEndListRespDto {
    private PageInfo<RouteHallEndDto> page_data;

    public PageInfo<RouteHallEndDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<RouteHallEndDto> page_data) {
        this.page_data = page_data;
    }
}
