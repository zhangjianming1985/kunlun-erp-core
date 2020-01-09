package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteMotorcadeDto
 * @Description 线路团关联车队数据
 * @Author Jm.zhang
 * @Date 2019-12-22 21:55
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联车队数据")
public class RouteMotorcadeDto {
    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String motorcade_code;

    @ApiModelProperty(required = true,value = "供应商编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(value = "供应商名称,新增时 无需传参",example = "兴旺车队公司")
    private String company_name;

    @ApiModelProperty(required = true,value = "联系人编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String person_code;

    @ApiModelProperty(value = "联系人名字,新增时 无需传参",example = "小王")
    private String person_name;

    @ApiModelProperty(value = "联系人手机号码,新增时 无需传参",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(required = true,value = "用车日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.MOTORCADE_START_DATE_INVALID)
    private String start_date;

    @ApiModelProperty(required = true,value = "返程日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.MOTORCADE_END_DATE_INVALID)
    private String end_date;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotBlank(message = ErrorCodeConstant.MOTORCADE_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "10.50")
    @NotBlank(message = ErrorCodeConstant.MOTORCADE_PRICE_INVALID)
    private String fee;

    @ApiModelProperty(required = true,value = "用车数量",example = "5")
    @NotNull(message = ErrorCodeConstant.MOTORCADE_CAR_COUNT_INVALID)
    private Integer car_count;

    @ApiModelProperty(required = true,value = "总价",example = "105.00")
    @NotBlank(message = ErrorCodeConstant.MOTORCADE_TOTAL_PRICE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getMotorcade_code() {
        return motorcade_code;
    }

    public void setMotorcade_code(String motorcade_code) {
        this.motorcade_code = motorcade_code;
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

    public Integer getCar_count() {
        return car_count;
    }

    public void setCar_count(Integer car_count) {
        this.car_count = car_count;
    }

    public String getFee_total() {
        return fee_total;
    }

    public void setFee_total(String fee_total) {
        this.fee_total = fee_total;
    }
    public Double getFee_total_double() {
        return new BigDecimal(fee_total).doubleValue();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
