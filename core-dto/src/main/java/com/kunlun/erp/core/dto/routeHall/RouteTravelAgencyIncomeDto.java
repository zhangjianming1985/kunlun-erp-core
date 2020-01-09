package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteTravelAgencyIncomeDto
 * @Description 线路团地接款
 * @Author Jm.zhang
 * @Date 2019/12/26 14:53
 * @Version 1.0
 **/
@ApiModel(description = "线路团地接款")
public class RouteTravelAgencyIncomeDto {

    @ApiModelProperty(value = "数据唯一编号，创建时无需传参",example = "6668866")
    private String income_code;

    @ApiModelProperty(value = "地接供应商名称，回显用",example = "兴旺公司")
    private String company_name;

    @ApiModelProperty(required = true,value = "费用类型，0=成人打包价、1=儿童打包价、2=导游/领队、3=交通票务、4=住宿、5=景点门票、6=用餐、7=用车、8=保险、9=地接、10其他",example = "0")
    private Integer fee_type;


    @ApiModelProperty(value = "创建时无需传参，费用类型解释，0=成人打包价、1=儿童打包价、2=导游/领队、3=交通票务、4=住宿、5=景点门票、6=用餐、7=用车、8=保险、9=地接、10其他",example = "用车")
    private String fee_type_str;

    @ApiModelProperty(value = "说明",example = "成人打包价格")
    private String fee_description;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "119.65")
    private String price;

    @ApiModelProperty(required = true,value = "数量",example = "1")
    private Integer quantity;

    @ApiModelProperty(required = true,value = "总价",example = "119.65")
    private String total_price;

    public String getIncome_code() {
        return income_code;
    }

    public void setIncome_code(String income_code) {
        this.income_code = income_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getFee_type() {
        return fee_type;
    }

    public void setFee_type(Integer fee_type) {
        this.fee_type = fee_type;
        this.setFee_type_str(SysConstant.RouteFeeType.getSRouteFeeType(this.fee_type).getName());
    }

    public String getFee_type_str() {
        return fee_type_str;
    }

    public void setFee_type_str(String fee_type_str) {
        this.fee_type_str = fee_type_str;
    }

    public String getFee_description() {
        return fee_description;
    }

    public void setFee_description(String fee_description) {
        this.fee_description = fee_description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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


    public Double getTotal_price_double(){
        return Double.valueOf(this.total_price);
    }


    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

}
