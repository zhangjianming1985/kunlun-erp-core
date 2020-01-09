package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName PricePlanDelReqDto
 * @Description 删除价格套餐
 * @Author Jm.zhang
 * @Date 2019/12/17 19:48
 * @Version 1.0
 **/
@ApiModel(description = "删除价格套餐参数")
public class PricePlanDelReqDto {
    @ApiModelProperty(required = true,value = "价格套餐编号",example = "50000000000")
    @NotBlank(message = ErrorCodeConstant.PRICE_PLAN_CODE_INVALID)
    private String price_plan_code;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }
}
