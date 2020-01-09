package com.kunlun.erp.core.dto.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName PaymentDto
 * @Description 供应商应付、应收 款数据
 * @Author Jm.zhang
 * @Date 2019/12/27 18:27
 * @Version 1.0
 **/
@ApiModel(description = "供应商应付、应收 款数据")
public class PaymentDto {

    @ApiModelProperty(value = "交易唯一码",example = "888554888")
    private String trade_code;

    @ApiModelProperty(value = "团号",example = "888554888")
    private String group_code;

    @ApiModelProperty(value = "供应商编号",example = "888554888")
    private String company_code;

    @ApiModelProperty(value = "供应商名称",example = "天王渠道")
    private String company_name;

    @ApiModelProperty(required = true,value = "财务结算模式：0=现结（实时）结算、1=周结、2=月结、3=季结、4=年结",example = "2")
    private Integer settlement_mode;

    @ApiModelProperty(required = true,value = "财务结算模式释义",example = "月结")
    private String settlement_mode_str;

    @ApiModelProperty(value = "应向供应商付款金额",example = "50.00")
    private String amount_payable;

    @ApiModelProperty(value = "已付款金额",example = "40.00")
    private String  amount_paid;

    @ApiModelProperty(value = "供应商未付金额",example = "10.00")
    private String amount_not_pay;

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


    public String getAmount_payable() {
        return amount_payable;
    }

    public void setAmount_payable(String amount_payable) {
        this.amount_payable = amount_payable;
    }

    public String getAmount_not_pay() {
        return amount_not_pay;
    }

    public void setAmount_not_pay(String amount_not_pay) {
        this.amount_not_pay = amount_not_pay;
    }

    public Integer getSettlement_mode() {
        return settlement_mode;
    }

    public void setSettlement_mode(Integer settlement_mode) {
        this.settlement_mode = settlement_mode;
    }

    public String getSettlement_mode_str() {
        return settlement_mode_str;
    }

    public void setSettlement_mode_str(String settlement_mode_str) {
        this.settlement_mode_str = settlement_mode_str;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getTrade_code() {
        return trade_code;
    }

    public void setTrade_code(String trade_code) {
        this.trade_code = trade_code;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

}
