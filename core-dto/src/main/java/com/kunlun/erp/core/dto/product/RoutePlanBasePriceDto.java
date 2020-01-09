package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RoutePlanBasePriceDto
 * @Description 线路计划价格明细
 * @Author Jm.zhang
 * @Date 2019/12/9 9:52
 * @Version 1.0
 **/
@ApiModel(description = "线路计划价格数据")
public class RoutePlanBasePriceDto {
    @ApiModelProperty(value = "价格编号，创建时无需传参",example = "858888888")
    private String base_price_code;

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
    private String  total_price;

    public Integer getFee_type() {
        return fee_type;
    }

    public void setFee_type(Integer fee_type) {
        this.fee_type = fee_type;
        this.setFee_type_str(SysConstant.RouteFeeType.getSRouteFeeType(this.fee_type).getName());
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


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getBase_price_code() {
        return base_price_code;
    }

    public void setBase_price_code(String base_price_code) {
        this.base_price_code = base_price_code;
    }

    public String getFee_type_str() {
        return fee_type_str;
    }

    public void setFee_type_str(String fee_type_str) {
        this.fee_type_str = fee_type_str;
    }
}
