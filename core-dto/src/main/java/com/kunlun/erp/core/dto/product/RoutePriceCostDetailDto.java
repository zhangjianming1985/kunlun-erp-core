package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RoutePriceCostDetailDto
 * @Description 成本明细
 * @Author Jm.zhang
 * @Date 2019/12/17 9:24
 * @Version 1.0
 **/
@ApiModel(description = "价格日历 成本明细")
public class RoutePriceCostDetailDto {
    @ApiModelProperty(value = "成本项编号，创建时无需传参",example = "555555555")
    private String cost_code;

    @ApiModelProperty(value = "价格编号，创建时无需传参",example = "555555555")
    private String price_code;

    @ApiModelProperty(value = "日期，格式yyyy-MM-dd",example = "2019-12-12")
    private String departure_date;

    @ApiModelProperty(required = true,value = "成本类型，0=成人打包价、1=儿童打包价、2=导游/领队、3=交通票务、4=住宿、5=景点门票、6=用餐、7=用车、8=保险、9=地接、10其他",example = "0")
    private Integer cost_type;

    @ApiModelProperty(value = "成本类型释义，创建时无需传参",example = "成人打包价格")
    private String cost_type_str;

    @ApiModelProperty(value = "成本描述",example = "这是一个成本描述")
    private String cost_description;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "119.65")
    private String cost_price;

    @ApiModelProperty(required = true,value = "数量",example = "1")
    private Integer quantity;

    @ApiModelProperty(required = true,value = "总价",example = "119.65")
    private String total_price;

    public String getCost_code() {
        return cost_code;
    }

    public void setCost_code(String cost_code) {
        this.cost_code = cost_code;
    }

    public String getPrice_code() {
        return price_code;
    }

    public void setPrice_code(String price_code) {
        this.price_code = price_code;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public Integer getCost_type() {
        return cost_type;
    }

    public void setCost_type(Integer cost_type) {
        this.cost_type = cost_type;
        this.setCost_type_str(SysConstant.RouteFeeType.getSRouteFeeType(this.cost_type).getName());
    }

    public String getCost_type_str() {
        return cost_type_str;
    }

    public void setCost_type_str(String cost_type_str) {
        this.cost_type_str = cost_type_str;
    }

    public String getCost_description() {
        return cost_description;
    }

    public void setCost_description(String cost_description) {
        this.cost_description = cost_description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
