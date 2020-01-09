package com.kunlun.erp.core.dto.company.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName FinancialAccountListReqDto
 * @Description 获取金融账户参数
 * @Author Jm.zhang
 * @Date 2019/12/16 12:33
 * @Version 1.0
 **/
@ApiModel(description = "获取金融账户参数")
public class FinancialAccountListReqDto {
    @ApiModelProperty(value = "企业编号")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }
}
