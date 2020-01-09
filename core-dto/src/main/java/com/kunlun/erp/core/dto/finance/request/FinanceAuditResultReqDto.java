package com.kunlun.erp.core.dto.finance.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName FinanceAuditResultReqDto
 * @Description 获取审核结果
 * @Author Jm.zhang
 * @Date 2019/12/27 12:28
 * @Version 1.0
 **/
@ApiModel(description ="获取审核结果" )
public class FinanceAuditResultReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
