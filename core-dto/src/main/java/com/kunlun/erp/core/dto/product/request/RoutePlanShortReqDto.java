package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RoutePlanShortReqDto
 * @Description 获取线路产品行程方案参数
 * @Author Jm.zhang
 * @Date 2019/12/16 17:37
 * @Version 1.0
 **/
@ApiModel(description = "获取线路产品行程方案参数")
public class RoutePlanShortReqDto {

    @ApiModelProperty(required = true,value = "产品编号",example = "6000001850269786")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_IS_NOT_EXIST)
    private String product_code;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
