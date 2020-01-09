package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RouteProductDelReqDto
 * @Description 删除线路产品
 * @Author Jm.zhang
 * @Date 2019/12/31 16:51
 * @Version 1.0
 **/
@ApiModel(description = "删除线路产品")
public class RouteProductDelReqDto {
    @ApiModelProperty(required = true,value = "产品编号",example = "6000001165987885")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_IS_NOT_EXIST)
    private String product_code;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
