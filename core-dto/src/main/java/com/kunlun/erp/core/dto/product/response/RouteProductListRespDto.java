package com.kunlun.erp.core.dto.product.response;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName RouteProductListRespDto
 * @Description 线路产品列表响应
 * @Author Jm.zhang
 * @Date 2019/12/9 17:41
 * @Version 1.0
 **/
@ApiModel(description = "线路产品列表响应")
public class RouteProductListRespDto {

    private PageInfo<RouteProductListDto> page_data;

    public PageInfo<RouteProductListDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<RouteProductListDto> page_data) {
        this.page_data = page_data;
    }
}
