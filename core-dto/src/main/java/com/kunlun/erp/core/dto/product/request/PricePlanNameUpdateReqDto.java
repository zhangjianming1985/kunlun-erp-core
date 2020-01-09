package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName PricePlanNameUpdateReqDto
 * @Description 修改价格套餐名称
 * @Author Jm.zhang
 * @Date 2019/12/18 11:28
 * @Version 1.0
 **/
@ApiModel(description = "修改价格套餐名称")
public class PricePlanNameUpdateReqDto {
    @ApiModelProperty(required = true,value = "价格方案编号",example = "125855")
    @NotBlank(message = ErrorCodeConstant.PRICE_PLAN_CODE_INVALID)
    private String  price_plan_code;

    @ApiModelProperty(required = true,value = "价格方案名称",example = "价格方案一")
    @NotBlank(message = ErrorCodeConstant.PRICE_PLAN_NAME_INVALID)
    private String price_plan_name;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }

    public String getPrice_plan_name() {
        return price_plan_name;
    }

    public void setPrice_plan_name(String price_plan_name) {
        this.price_plan_name = price_plan_name;
    }
}
