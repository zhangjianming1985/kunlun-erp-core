package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProductCategoryUpdateRespDto
 * @Description 产品类别修改结果响应
 * @Author Jm.zhang
 * @Date 2019/12/6 15:35
 * @Version 1.0
 **/
@ApiModel(description = "产品类型修改结果响应")
public class ProductCategoryUpdateRespDto {
    @ApiModelProperty(value = "被修改的类别编号")
    private String category_code;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }
}
