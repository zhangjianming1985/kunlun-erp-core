package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ProductCategoryDelReqDto
 * @Description 产品分类删除请求
 * @Author Jm.zhang
 * @Date 2019/12/31 12:05
 * @Version 1.0
 **/
@ApiModel(description = "产品分类删除请求")
public class ProductCategoryDelReqDto {
    @ApiModelProperty(value = "产品类别编号")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_CATEGORY_CODE_INVALID)
    private String category_code;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

}
