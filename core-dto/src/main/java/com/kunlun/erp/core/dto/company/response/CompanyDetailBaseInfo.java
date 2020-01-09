package com.kunlun.erp.core.dto.company.response;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.company.BaseInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CompanyDetailBaseInfo
 * @Description 企业基础信息
 * @Author Jm.zhang
 * @Date 2019/11/28 12:45
 * @Version 1.0
 **/
@ApiModel(description = "基础信息部分")
public class CompanyDetailBaseInfo extends BaseInfoDto {

    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    private Integer company_type;
    @ApiModelProperty(value = "企业唯一编号",example = "1000000123963044")
    private String company_code;
    @ApiModelProperty(value = "企业类型说明",example = "旅行社")
    private String company_type_str;
    @ApiModelProperty(value = "所属平台说明",example = "飞猪")
    private String belong_platform_str;
    @ApiModelProperty(value = "合作状态说明",example = "已合作")
    private String cooperation_state_str;
    @ApiModelProperty(value = "合同签署状态说明",example = "未签署")
    private String contract_document_state_str;


    public String getCompany_type_str() {
        return company_type_str;
    }

    public void setCompany_type_str(String company_type_str) {
        this.company_type_str = company_type_str;
    }

    public String getBelong_platform_str() {
        if (SysConstant.BelongPlatform.getBelongPlatform(super.getBelong_platform()) ==null){
            return "";
        }
        return SysConstant.BelongPlatform.getBelongPlatform(super.getBelong_platform()).getName();
    }

    public void setBelong_platform_str(String belong_platform_str) {
        this.belong_platform_str = belong_platform_str;
    }

    public String getCooperation_state_str() {
        return SysConstant.CooperationState.getCooperationState(super.getCooperation_state()).getName();
    }

    public void setCooperation_state_str(String cooperation_state_str) {
        this.cooperation_state_str = cooperation_state_str;
    }

    public String getContract_document_state_str() {
        return SysConstant.ContractDocumentState.getContractDocumentState(super.getContract_document_state()).getName();
    }

    public void setContract_document_state_str(String contract_document_state_str) {
        this.contract_document_state_str = contract_document_state_str;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
        this.setCompany_type_str(SysConstant.CompanyType.getCompanyType(this.company_type).getName());
    }
}
