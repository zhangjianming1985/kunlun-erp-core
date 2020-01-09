package com.kunlun.erp.core.dto.routeOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName OrderClientDto
 * @Description 订单游客数据
 * @Author Jm.zhang
 * @Date 2019/12/23 11:27
 * @Version 1.0
 **/
@ApiModel(description ="订单游客数据" )
public class OrderClientDto {

    @ApiModelProperty(value = "游客编号，创建时无需传参",example = "6668866")
    private String client_code;

    @ApiModelProperty(value = "游客名字",example = "李四")
    private String client_name;

    @ApiModelProperty(value = "游客手机号码",example = "15915328866")
    private String client_mobile;

    @ApiModelProperty(value = "证件类型：1=身份证、2=护照、99=其他",example = "0")
    private Integer card_type;

    @ApiModelProperty(value = "证件号码",example = "13049844454")
    private String card_number;

    @ApiModelProperty(value = "客户类型：0=成人、1=儿童",example = "0")
    private Integer client_type;


    @ApiModelProperty(value = "出生日期",example = "1988-11-19")
    private String client_birthday;

    @ApiModelProperty(value = "性别：1=男、2=女",example = "0")
    private Integer client_sex;

    @ApiModelProperty(value = "年龄",example = "28")
    private Integer client_age;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    @ApiModelProperty(value = "交通票务状态：0=不预定、1=确认、2=占位",example = "1")
    private Integer ticket_state;


    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_mobile() {
        return client_mobile;
    }

    public void setClient_mobile(String client_mobile) {
        this.client_mobile = client_mobile;
    }

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Integer getClient_type() {
        return client_type;
    }

    public void setClient_type(Integer client_type) {
        this.client_type = client_type;
    }

    public String getClient_birthday() {
        return client_birthday;
    }

    public void setClient_birthday(String client_birthday) {
        this.client_birthday = client_birthday;
    }

    public Integer getClient_sex() {
        return client_sex;
    }

    public void setClient_sex(Integer client_sex) {
        this.client_sex = client_sex;
    }

    public Integer getClient_age() {
        return client_age;
    }

    public void setClient_age(Integer client_age) {
        this.client_age = client_age;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getTicket_state() {
        return ticket_state;
    }

    public void setTicket_state(Integer ticket_state) {
        this.ticket_state = ticket_state;
    }
}
