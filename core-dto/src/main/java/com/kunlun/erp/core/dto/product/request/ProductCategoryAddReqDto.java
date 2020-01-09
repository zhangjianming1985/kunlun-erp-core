package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ProductCategoryAddReqDto
 * @Description 添加产品类别
 * @Author Jm.zhang
 * @Date 2019/12/6 11:36
 * @Version 1.0
 **/
@ApiModel(description = "创建产品类别")
public class ProductCategoryAddReqDto {

    @ApiModelProperty(value = "产品类别名称")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_CATEGORY_NAME_INVALID)
    private String category_name;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
