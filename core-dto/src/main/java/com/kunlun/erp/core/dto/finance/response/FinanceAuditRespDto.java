package com.kunlun.erp.core.dto.finance.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName FinanceAuditRespDto
 * @Description 财务审核结果
 * @Author Jm.zhang
 * @Date 2019/12/27 10:12
 * @Version 1.0
 **/
@ApiModel(description = "财务审核结果")
public class FinanceAuditRespDto {
    @ApiModelProperty(value = "团号",example ="13000001546253086" )
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
