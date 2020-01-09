package com.kunlun.erp.core.dto.finance.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName FinanceAuditReqDto
 * @Description 审核财务数据
 * @Author Jm.zhang
 * @Date 2019/12/27 10:07
 * @Version 1.0
 **/
@ApiModel(description = "审核财务数据")
public class FinanceAuditReqDto {
    @ApiModelProperty(value = "团号",example ="13000001546253086" )
    private String group_code;

    @ApiModelProperty(value = "审核状态,0=未审核、1=通过、2=驳回",example ="0" )
    private Integer approve_state;

    @ApiModelProperty(value = "审核备注",example ="这是一个备注" )
    private String approve_remarks;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Integer getApprove_state() {
        return approve_state;
    }

    public void setApprove_state(Integer approve_state) {
        this.approve_state = approve_state;
    }

    public String getApprove_remarks() {
        return approve_remarks;
    }

    public void setApprove_remarks(String approve_remarks) {
        this.approve_remarks = approve_remarks;
    }
}
