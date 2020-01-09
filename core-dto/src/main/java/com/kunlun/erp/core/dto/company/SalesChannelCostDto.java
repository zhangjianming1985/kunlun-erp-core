package com.kunlun.erp.core.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName SalesChannelCostDto
 * @Description 销售渠道费用数据
 * @Author Jm.zhang
 * @Date 2019/12/4 16:31
 * @Version 1.0
 **/
@ApiModel(description = "销售渠道费用数据")
public class SalesChannelCostDto {

    @ApiModelProperty(required = true,value = "销售渠道费用数据编号,创建时 无需传参",example = "700085115254")
    private String cost_code;

    @ApiModelProperty(required = true,value = "企业编号,创建时 无需传参",example = "500085115254")
    private String company_code;

    @ApiModelProperty(required = true,value = "费用类型ID，0=广告费用、1=交易费用、9=其他",example = "0")
    private  Integer cost_type_id;

    @ApiModelProperty(required = true,value = "产品类别编号",example = "655554115455")
    private String product_category_code;

    @ApiModelProperty(required = true,value = "销售渠道费用结算模式,0=底价模式、1=佣金模式、9=其他模式",example = "0")
    private Integer cost_settlement_mode;

    @ApiModelProperty(required = true,value = "销售渠道收费模式,0=年费、9=其他模式",example = "0")
    private Integer charge_mode;

    @ApiModelProperty(required = true,value = "费用",example = "2100.05")
    private String fee;

    @ApiModelProperty(required = true,value = "费率",example = "2100.05")
    private String rate;

    public String getCost_code() {
        return cost_code;
    }

    public void setCost_code(String cost_code) {
        this.cost_code = cost_code;
    }

    public Integer getCost_type_id() {
        return cost_type_id;
    }

    public void setCost_type_id(Integer cost_type_id) {
        this.cost_type_id = cost_type_id;
    }

    public String getProduct_category_code() {
        return product_category_code;
    }

    public void setProduct_category_code(String product_category_code) {
        this.product_category_code = product_category_code;
    }

    public Integer getCost_settlement_mode() {
        return cost_settlement_mode;
    }

    public void setCost_settlement_mode(Integer cost_settlement_mode) {
        this.cost_settlement_mode = cost_settlement_mode;
    }

    public Integer getCharge_mode() {
        return charge_mode;
    }

    public void setCharge_mode(Integer charge_mode) {
        this.charge_mode = charge_mode;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }
}
