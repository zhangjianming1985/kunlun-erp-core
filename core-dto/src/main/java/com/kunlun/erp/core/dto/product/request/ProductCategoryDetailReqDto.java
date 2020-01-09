package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ProductCategoryDetailReqDto
 * @Description 获取产品类别详细信息
 * @Author Jm.zhang
 * @Date 2019/12/6 14:10
 * @Version 1.0
 **/
@ApiModel(description = "获取产品类别详情参数")
public class ProductCategoryDetailReqDto {
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
