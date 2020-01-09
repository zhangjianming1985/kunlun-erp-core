package com.kunlun.erp.core.dto.product.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.product.ProductCategoryDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName ProductCategoryListRespDto
 * @Description 产品类别列表响应
 * @Author Jm.zhang
 * @Date 2019/12/6 9:51
 * @Version 1.0
 **/
@ApiModel(description = "产品类别列表响应")
public class ProductCategoryListRespDto {
    private PageInfo<ProductCategoryDto> page_data;

    public PageInfo<ProductCategoryDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<ProductCategoryDto> page_data) {
        this.page_data = page_data;
    }
}
