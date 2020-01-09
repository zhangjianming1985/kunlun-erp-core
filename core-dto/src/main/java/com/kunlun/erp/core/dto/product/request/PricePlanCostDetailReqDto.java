package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName PricePlanCostDetailReqDto
 * @Description 获取线路产品的成本明细
 * @Author Jm.zhang
 * @Date 2019/12/18 8:40
 * @Version 1.0
 **/
@ApiModel(description = "获取线路产品的成本明细")
public class PricePlanCostDetailReqDto {
    @ApiModelProperty(required = true,value = "价格套餐编号",example = "33333")
    @NotBlank(message = ErrorCodeConstant.PRICE_PLAN_CODE_INVALID)
    private String price_plan_code;

    @ApiModelProperty(required = true,value = "发团日期",example = "2020-01-01")
    @NotBlank(message = ErrorCodeConstant.PRICE_DETAIL_DEPARTURE_DATE_INVALID)
    private String departure_date;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }
}
