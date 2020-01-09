package com.kunlun.erp.core.dto.company.response;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CompanyListDto
 * @Description 响应DTO
 * @Author Jm.zhang
 * @Date 2019/11/26 16:46
 * @Version 1.0
 **/
@ApiModel(description = "企业信息")
public class CompanyListDto {
    @ApiModelProperty(value = "企业类型")
    private Integer company_type;

    @ApiModelProperty(value = "企业编号")
    private String company_code;

    @ApiModelProperty(value = "企业名称")
    private String company_name;

    @ApiModelProperty(value = "负责人姓名")
    private String leader_name;

    @ApiModelProperty(value = "线上销售渠道所归属的平台ID")
    private Integer belong_platform;

    @ApiModelProperty(value = "线上销售渠道所归属的平台名称")
    private String belong_platform_str;

    @ApiModelProperty(value = "区域信息")
    private String area_text;

    @ApiModelProperty(value = "合作开始日期")
    private String cooperation_start_date;

    @ApiModelProperty(value = "合作结束日期")
    private String cooperation_end_date;

    @ApiModelProperty(value = "创建人")
    private String creator_name;


    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getArea_text() {
        return area_text;
    }

    public void setArea_text(String area_text) {
        this.area_text = area_text;
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

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public Integer getBelong_platform() {
        return belong_platform;
    }

    public void setBelong_platform(Integer belong_platform) {
        this.belong_platform = belong_platform;
        if (this.belong_platform!=null){
            this.setBelong_platform_str(SysConstant.BelongPlatform.getBelongPlatform(this.belong_platform).getName());
        }
    }

    public String getBelong_platform_str() {
        return belong_platform_str;
    }

    public void setBelong_platform_str(String belong_platform_str) {
        this.belong_platform_str = belong_platform_str;
    }
}
