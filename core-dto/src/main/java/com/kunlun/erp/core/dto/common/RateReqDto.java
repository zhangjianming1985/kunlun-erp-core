package com.kunlun.erp.core.dto.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RateReqDto
 * @Description 货币转换请求
 * @Author Jm.zhang
 * @Date 2020/1/2 10:05
 * @Version 1.0
 **/
@ApiModel(description = "货币转换请求")
public class RateReqDto {
    @ApiModelProperty(required = true,value = "源货币，货币：USD、CNY . 目前仅支持 USD")
    @NotBlank(message = ErrorCodeConstant.SOURCE_CURRENCY_INVALID)
    private String source_currency;

    @ApiModelProperty(required = true,value = "目标货币，货币：USD、CNY. 目前仅支持 CNY")
    @NotBlank(message = ErrorCodeConstant.TARGET_CURRENCY_INVALID)
    private String target_currency;

    public String getSource_currency() {
        return source_currency;
    }

    public void setSource_currency(String source_currency) {
        this.source_currency = source_currency;
    }

    public String getTarget_currency() {
        return target_currency;
    }

    public void setTarget_currency(String target_currency) {
        this.target_currency = target_currency;
    }
}
