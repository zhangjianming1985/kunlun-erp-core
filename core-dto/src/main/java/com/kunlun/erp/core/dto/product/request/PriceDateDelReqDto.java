package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName PriceDateDelReqDto
 * @Description 删除某个日期的价格
 * @Author Jm.zhang
 * @Date 2019/12/18 10:28
 * @Version 1.0
 **/
@ApiModel(description = "删除某个日期的价格")
public class PriceDateDelReqDto {
    @ApiModelProperty(required = true,value = "价格套餐编号",example = "50000000000")
    @NotBlank(message = ErrorCodeConstant.PRICE_PLAN_CODE_INVALID)
    private String price_plan_code;
    @ApiModelProperty(required = true,value = "日期，格式 yyyy-MM-dd",example = "2019-12-12")
    private List<String> date_info;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }

    public List<String> getDate_info() {
        return date_info;
    }

    public void setDate_info(List<String> date_info) {
        this.date_info = date_info;
    }
}
