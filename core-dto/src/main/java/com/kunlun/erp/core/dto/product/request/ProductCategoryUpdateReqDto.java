package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ProductCategoryUpdateReqDto
 * @Description 修改产品类别信息
 * @Author Jm.zhang
 * @Date 2019/12/6 15:30
 * @Version 1.0
 **/
@ApiModel(description = "修改产品类别信息")
public class ProductCategoryUpdateReqDto {

    @ApiModelProperty(value = "产品类别编号")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_CATEGORY_CODE_INVALID)
    private String category_code;

    @ApiModelProperty(value = "产品类别名称")
    private String category_name;

    @ApiModelProperty(value = "产品类别状态，0=启用 1=禁用")
    private Integer category_state;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_state() {
        return category_state;
    }

    public void setCategory_state(Integer category_state) {
        this.category_state = category_state;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }
}
