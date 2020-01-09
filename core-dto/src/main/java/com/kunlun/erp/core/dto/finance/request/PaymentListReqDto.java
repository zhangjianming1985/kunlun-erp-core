package com.kunlun.erp.core.dto.finance.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName PaymentListReqDto
 * @Description 供应商应收应付数据检索请求
 * @Author Jm.zhang
 * @Date 2019-12-29 14:18
 * @Version 1.0
 **/
@ApiModel(description = "供应商应收应付数据检索请求")
public class PaymentListReqDto {
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(value = "供应商名称",example = "卖拖鞋的")
    private String company_name;

    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    private String group_code;


    @ApiModelProperty(required = true,value = "收付款状态,0=未付款、1=已付款",example = "0")
    private Integer payment_state;

    @ApiModelProperty(value = "收付款账号；",example = "621058988722244889665")
    private String account_no;

    @ApiModelProperty(value = "收付款开始日期",example = "2019-10-01")
    private String operation_star_date;

    @ApiModelProperty(value = "收付款结束日期",example = "2019-10-10")
    private String operation_end_date;

    @ApiModelProperty(value = "唯一编号结合")
    private List<String> trade_code;

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Integer getPayment_state() {
        return payment_state;
    }

    public void setPayment_state(Integer payment_state) {
        this.payment_state = payment_state;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getOperation_star_date() {
        return operation_star_date;
    }

    public void setOperation_star_date(String operation_star_date) {
        this.operation_star_date = operation_star_date;
    }

    public String getOperation_end_date() {
        return operation_end_date;
    }

    public void setOperation_end_date(String operation_end_date) {
        this.operation_end_date = operation_end_date;
    }

    public List<String> getTrade_code() {
        return trade_code;
    }

    public void setTrade_code(List<String> trade_code) {
        this.trade_code = trade_code;
    }
}
