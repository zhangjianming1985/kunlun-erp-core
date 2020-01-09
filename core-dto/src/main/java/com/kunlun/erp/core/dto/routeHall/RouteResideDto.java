package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteResideDto
 * @Description 线路团关联住宿数据
 * @Author Jm.zhang
 * @Date 2019/12/20 16:11
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联住宿数据")
public class RouteResideDto {

    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String reside_code;

    @ApiModelProperty(required = true,value = "供应商编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(value = "供应商名称,新增时 无需传参",example = "兴旺旅游公司")
    private String company_name;

    @ApiModelProperty(required = true,value = "联系人编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String person_code;

    @ApiModelProperty(value = "联系人名字,新增时 无需传参",example = "小王")
    private String person_name;

    @ApiModelProperty(value = "联系人手机号码,新增时 无需传参",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(value = "0=标准间、1=大床房、2=三人间、3=无房",example = "3")
    @NotNull(message = ErrorCodeConstant.RESIDE_ROOM_TYPE_INVALID)
    private Integer room_type;

    @ApiModelProperty(required = true,value = "入住日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotNull(message = ErrorCodeConstant.RESIDE_START_DATE_INVALID)
    private String start_date;

    @ApiModelProperty(required = true,value = "离店日期 格式 yyyy-MM-dd",example = "2020-01-05")
    @NotNull(message = ErrorCodeConstant.RESIDE_END_DATE_INVALID)
    private String end_date;

    @ApiModelProperty(required = true,value = "入住天数",example = "10")
    @NotNull(message = ErrorCodeConstant.RESIDE_DAYS_INVALID)
    private Integer days;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotNull(message = ErrorCodeConstant.RESIDE_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "10.50")
    @NotNull(message = ErrorCodeConstant.RESIDE_FEE_INVALID)
    private String fee;

    @ApiModelProperty(required = true,value = "房间数量",example = "5")
    @NotNull(message = ErrorCodeConstant.RESIDE_ROOM_COUNT_INVALID)
    private Integer room_count;

    @ApiModelProperty(required = true,value = "总价",example = "105.00")
    @NotNull(message = ErrorCodeConstant.RESIDE_TOTAL_FEE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getReside_code() {
        return reside_code;
    }

    public void setReside_code(String reside_code) {
        this.reside_code = reside_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
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

    public Integer getRoom_type() {
        return room_type;
    }

    public void setRoom_type(Integer room_type) {
        this.room_type = room_type;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getRoom_count() {
        return room_count;
    }

    public void setRoom_count(Integer room_count) {
        this.room_count = room_count;
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
}
