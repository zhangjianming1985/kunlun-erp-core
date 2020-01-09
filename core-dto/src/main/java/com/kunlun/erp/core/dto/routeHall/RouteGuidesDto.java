package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteGuidesDto
 * @Description 线路团的导游数据
 * @Author Jm.zhang
 * @Date 2019/12/19 17:39
 * @Version 1.0
 **/
@ApiModel(description = "线路团的导服数据")
public class RouteGuidesDto {
    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String guide_code;

    @ApiModelProperty(required = true,value = "导服供应商编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(value = "导服供应商名称,新增时 无需传参",example = "兴旺旅游公司")
    private String company_name;

    @ApiModelProperty(required = true,value = "导游编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String person_code;

    @ApiModelProperty(value = "导游名字,新增时 无需传参",example = "小王")
    private String person_name;

    @ApiModelProperty(value = "导游手机号码,新增时 无需传参",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(required = true,value = "带团人数",example = "10")
    @NotNull(message = ErrorCodeConstant.GUIDE_CLIENT_COUNT_INVALID)
    private Integer client_count;

    @ApiModelProperty(required = true,value = "带团日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.GUIDE_START_DATE_INVALID)
    private String start_date;

    @ApiModelProperty(required = true,value = "散团日期 格式 yyyy-MM-dd",example = "2020-01-05")
    @NotBlank(message = ErrorCodeConstant.GUIDE_END_DATE_INVALID)
    private String end_date;

    @ApiModelProperty(required = true,value = "带团团天数",example = "10")
    @NotBlank(message = ErrorCodeConstant.GUIDE_DAYS_INVALID)
    private Integer days;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotBlank(message = ErrorCodeConstant.GUIDE_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "每天服务费",example = "10.50")
    @NotBlank(message = ErrorCodeConstant.GUIDE_DAILY_FEE_INVALID)
    private String fee_daily;

    @ApiModelProperty(required = true,value = "服务费总计",example = "105.00")
    @NotBlank(message = ErrorCodeConstant.GUIDE_TOTAL_FEE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getGuide_code() {
        return guide_code;
    }

    public void setGuide_code(String guide_code) {
        this.guide_code = guide_code;
    }


    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public Integer getClient_count() {
        return client_count;
    }

    public void setClient_count(Integer client_count) {
        this.client_count = client_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFee_daily() {
        return fee_daily;
    }

    public void setFee_daily(String fee_daily) {
        this.fee_daily = fee_daily;
    }

    public String getFee_total() {
        return fee_total;
    }

    public Double getFee_total_double() {
        return new BigDecimal(fee_total).doubleValue();
    }

    public void setFee_total(String fee_total) {
        this.fee_total = fee_total;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }
}
