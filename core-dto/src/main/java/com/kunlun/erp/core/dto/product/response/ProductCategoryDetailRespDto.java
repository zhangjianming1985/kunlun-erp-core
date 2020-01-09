package com.kunlun.erp.core.dto.product.response;

import com.kunlun.erp.core.dto.product.ProductCategoryDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName ProductCategoryDetailRespDto
 * @Description 产品类别详情响应
 * @Author Jm.zhang
 * @Date 2019/12/6 14:17
 * @Version 1.0
 **/
@ApiModel(description = "产品类别详情响应数据")
public class ProductCategoryDetailRespDto {

    private ProductCategoryDto category_info;

    public ProductCategoryDto getCategory_info() {
        return category_info;
    }

    public void setCategory_info(ProductCategoryDto category_info) {
        this.category_info = category_info;
    }
}
