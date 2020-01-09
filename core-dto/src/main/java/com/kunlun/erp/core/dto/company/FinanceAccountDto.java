package com.kunlun.erp.core.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName FinanceAccountDto
 * @Description 财务账号数据
 * @Author Jm.zhang
 * @Date 2019/11/18 14:57
 * @Version 1.0
 **/
@ApiModel(description = "金融账户信息")
public class FinanceAccountDto {
    @ApiModelProperty(value = "金融账户唯一编号,新增账户时 无需传参",example = "1000001626105399")
    private String account_code;

    @ApiModelProperty(value = "金融账号类型、0=银行卡、1=支付宝、2=微信",example = "0")
    private Integer account_type;

    @ApiModelProperty(value = "账户名",example = "张三")
    private String account_name;

    @ApiModelProperty(value = "账号，最多50个字符，文本内容；",example = "621058988722244889665")
    private String account_no;

    @ApiModelProperty(value ="开户行，最多50个字符；" ,example = "南山支行")
    private String bank_name;

    @ApiModelProperty(value = "是否默认账户：0=否、1=是；",example = "0")
    private Integer is_default;


    public Integer getAccount_type() {
        return account_type;
    }

    public void setAccount_type(Integer account_type) {
        this.account_type = account_type;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public String getAccount_code() {
        return account_code;
    }

    public void setAccount_code(String account_code) {
        this.account_code = account_code;
    }
}
