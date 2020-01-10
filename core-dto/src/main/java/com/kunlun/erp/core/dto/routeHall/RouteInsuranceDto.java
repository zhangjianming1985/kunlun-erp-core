package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteInsuranceDto
 * @Description 线路团关联保险数据
 * @Author Jm.zhang
 * @Date 2019-12-22 23:34
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联保险数据")
public class RouteInsuranceDto {
    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String insurance_code;

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

    @ApiModelProperty(value = "保险信息 0=意外险、1=责任险、2=统一险、3=线路含保险",example = "0")
    @NotNull(message = ErrorCodeConstant.INSURANCE_TYPE_INVALID)
    private Integer insurance_type;

    @ApiModelProperty(required = true,value = "开始日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.INSURANCE_START_DATE_INVALID)
    private String start_date;

    @ApiModelProperty(required = true,value = "结束日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.INSURANCE_END_DATE_INVALID)
    private String end_date;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotBlank(message = ErrorCodeConstant.INSURANCE_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "10.50")
    @NotBlank(message = ErrorCodeConstant.INSURANCE_PRICE_INVALID)
    private String fee;

    @ApiModelProperty(required = true,value = "投保数量",example = "5")
    @NotNull(message = ErrorCodeConstant.INSURANCE_COUNT_INVALID)
    private Integer insurance_count;

    @ApiModelProperty(required = true,value = "总价",example = "105.00")
    @NotBlank(message = ErrorCodeConstant.INSURANCE_TOTAL_PRICE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getInsurance_code() {
        return insurance_code;
    }

    public void setInsurance_code(String insurance_code) {
        this.insurance_code = insurance_code;
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

    public Integer getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(Integer insurance_type) {
        this.insurance_type = insurance_type;
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

    public Integer getInsurance_count() {
        return insurance_count;
    }

    public void setInsurance_count(Integer insurance_count) {
        this.insurance_count = insurance_count;
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
