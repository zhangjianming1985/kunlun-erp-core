package com.kunlun.erp.core.dto.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RateRespDto
 * @Description 货币转换响应
 * @Author Jm.zhang
 * @Date 2020/1/2 10:11
 * @Version 1.0
 **/
public class RateRespDto {
    @ApiModelProperty(value ="汇率",example = "6.9614")
    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
