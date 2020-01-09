package com.kunlun.erp.core.dto.finance.response;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName FinanceAuditResultRespDto
 * @Description 审核结果响应数据
 * @Author Jm.zhang
 * @Date 2019/12/27 12:30
 * @Version 1.0
 **/
@ApiModel(description = "审核结果响应数据")
public class FinanceAuditResultRespDto {
    @ApiModelProperty(value = "审核状态,0=未审核、1=通过、2=驳回",example = "2")
    private Integer audit_state;

    @ApiModelProperty(value = "审核状态释义",example = "通过")
    private String audit_state_str;

    @ApiModelProperty(value = "审核备注",example ="这是一个备注" )
    private String approve_remarks;

    public Integer getAudit_state() {
        return audit_state;
    }

    public void setAudit_state(Integer audit_state) {
        this.audit_state = audit_state;
        if (audit_state!=null){
            this.setAudit_state_str(SysConstant.FinanceAuditStatus.getFinanceAuditStatus(audit_state).getName());
        }
    }

    public String getAudit_state_str() {
        return audit_state_str;
    }

    public void setAudit_state_str(String audit_state_str) {
        this.audit_state_str = audit_state_str;
    }

    public String getApprove_remarks() {
        return approve_remarks;
    }

    public void setApprove_remarks(String approve_remarks) {
        this.approve_remarks = approve_remarks;
    }
}
