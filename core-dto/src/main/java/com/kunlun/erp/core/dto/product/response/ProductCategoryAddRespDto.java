package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProductCategoryAddRespDto
 * @Description 创建产品类别响应
 * @Author Jm.zhang
 * @Date 2019/12/6 11:40
 * @Version 1.0
 **/
@ApiModel(description = "创建产品类别响应")
public class ProductCategoryAddRespDto {
    @ApiModelProperty(value = "产品类别编号")
    private String category_code;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }
}
