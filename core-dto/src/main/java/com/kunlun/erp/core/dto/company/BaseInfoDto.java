package com.kunlun.erp.core.dto.company;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName BaseInfoDto
 * @Description 通用的基础信息
 * @Author Jm.zhang
 * @Date 2019/11/18 14:35
 * @Version 1.0
 **/
@ApiModel(description = "基础信息部分")
public class BaseInfoDto {


    @ApiModelProperty(required = true,value = "企业名称  必填，最多60个字符",example = "测试企业名称")
    @NotBlank(message = ErrorCodeConstant.COMPANY_NAME_MISSING)
    @Length(min=1,max = 60,message =  ErrorCodeConstant.COMPANY_NAME_CHAR_OUT_LENGTH)
    private String company_name;

    @ApiModelProperty(value = "销售渠道 所属平台,0=飞猪、1=携程、2=美团、3=去哪儿、4=马蜂窝、5=同程艺龙、6=途牛、7=驴妈妈、8=蜗友行、9=魔方云仓",example = "0")
    private Integer belong_platform;

    @ApiModelProperty(value = "营业执照图片地址 选填，附件统一大小不超过2M，超出提示”上传附件超出最大尺寸2M限制",example = "https://www.baidu.com/img/bd_logo1.png")
    private String business_licence_url;

    @ApiModelProperty(value = "经营许可证图片地址  选填，附件统一大小不超过2M，超出提示”上传附件超出最大尺寸2M限制",example = "https://www.baidu.com/img/bd_logo1.png")
    private String business_certificate;

    @ApiModelProperty(value = "详细地址，选填，最多80个字符",example = "北京市海淀区西北旺东路10号院百度科技园1号楼－5号楼")
    private String address;

    @ApiModelProperty(value = "简介，选填，最多1000个字符",example = "这是一个企业简介，这是一个企业简介，这是一个企业简介，这是一个企业简介")
    private String introduction;

    @ApiModelProperty(required = true,value = "合作状态  0=未合作、1=已合作、2=洽谈中",example = "1")
    @NotNull(message = ErrorCodeConstant.COOPERATION_STATE_MISSING)
    private Integer cooperation_state;

    @ApiModelProperty(required = true,value = "对接人ID，必填，点击弹出选择账号列表下用户，支持输入姓名下拉提示或2级联动选择，角色名/姓名；",example = "101")
    @NotNull(message = ErrorCodeConstant.CONTACT_PERSON_MISSING)
    private Integer contact_person;

    @ApiModelProperty(required = true,value = "合同签署状态，0=未签署、1=已签署",example = "0")
    @NotNull(message = ErrorCodeConstant.CONTRACT_DOCUMENT_STATE_MISSING)
    private Integer contract_document_state;

    @ApiModelProperty(value = "合同附件，选填，同执照附件要求",example ="https://www.baidu.com/img/bd_logo1.png")
    private String contract_document_url;

    @ApiModelProperty(value = "合作开始日期",example = "2019-12-01")
    private String cooperation_start_date;

    @ApiModelProperty(value = "合作结束日期",example = "2025-01-01")
    private String cooperation_end_date;

    @ApiModelProperty(value = "备注",example = "这是一个备注这是一个备注这是一个备注这是一个备注这是一个备注这是一个备注")
    private String remarks;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBusiness_licence_url() {
        return business_licence_url;
    }

    public void setBusiness_licence_url(String business_licence_url) {
        this.business_licence_url = business_licence_url;
    }

    public String getBusiness_certificate() {
        return business_certificate;
    }

    public void setBusiness_certificate(String business_certificate) {
        this.business_certificate = business_certificate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getCooperation_state() {
        return cooperation_state;
    }

    public void setCooperation_state(Integer cooperation_state) {
        this.cooperation_state = cooperation_state;
    }

    public Integer getContact_person() {
        return contact_person;
    }

    public void setContact_person(Integer contact_person) {
        this.contact_person = contact_person;
    }

    public Integer getContract_document_state() {
        return contract_document_state;
    }

    public void setContract_document_state(Integer contract_document_state) {
        this.contract_document_state = contract_document_state;
    }

    public String getContract_document_url() {
        return contract_document_url;
    }

    public void setContract_document_url(String contract_document_url) {
        this.contract_document_url = contract_document_url;
    }

    public String getCooperation_start_date() {
        return cooperation_start_date;
    }

    public void setCooperation_start_date(String cooperation_start_date) {
        this.cooperation_start_date = cooperation_start_date;
    }

    public String getCooperation_end_date() {
        return cooperation_end_date;
    }

    public void setCooperation_end_date(String cooperation_end_date) {
        this.cooperation_end_date = cooperation_end_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getBelong_platform() {
        return belong_platform;
    }

    public void setBelong_platform(Integer belong_platform) {
        this.belong_platform = belong_platform;
    }
}
