package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteTicketDto
 * @Description 线路团关联景点门票数据
 * @Author Jm.zhang
 * @Date 2019/12/20 17:48
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联景点门票数据")
public class RouteTicketDto {

    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String ticket_code;

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

    @ApiModelProperty(value = "门票类型：0=全价、1=半价、",example = "0")
    @NotNull(message = ErrorCodeConstant.TICKET_TYPE_INVALID)
    private Integer ticket_type;

    @ApiModelProperty(required = true,value = "游玩日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.TICKET_DATE_INVALID)
    private String ticket_date;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotBlank(message = ErrorCodeConstant.TICKET_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "10.50")
    @NotBlank(message = ErrorCodeConstant.TICKET_PRICE_INVALID)
    private String fee;

    @ApiModelProperty(required = true,value = "门票数量",example = "5")
    @NotNull(message = ErrorCodeConstant.TICKET_COUNT_INVALID)
    private Integer ticket_count;

    @ApiModelProperty(required = true,value = "总价",example = "105.00")
    @NotBlank(message = ErrorCodeConstant.TICKET_TOTAL_PRICE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getTicket_code() {
        return ticket_code;
    }

    public void setTicket_code(String ticket_code) {
        this.ticket_code = ticket_code;
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

    public Integer getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(Integer ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getTicket_date() {
        return ticket_date;
    }

    public void setTicket_date(String ticket_date) {
        this.ticket_date = ticket_date;
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

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
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
