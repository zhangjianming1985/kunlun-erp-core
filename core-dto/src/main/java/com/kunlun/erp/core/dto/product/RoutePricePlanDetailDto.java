package com.kunlun.erp.core.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RoutePricePlanDetailDto
 * @Description 线路产品价格套餐明细数据
 * @Author Jm.zhang
 * @Date 2019/12/16 17:06
 * @Version 1.0
 **/
@ApiModel(description = "线路产品价格套餐明细数据")
public class RoutePricePlanDetailDto {
    @ApiModelProperty(value = "价格套餐编号",example = "20000555555662358")
    private String price_plan_code;
    @ApiModelProperty(value = "出发日期,格式 yyyy-MM-dd",example = "2019-12-21")
    private String departure_date;

    @ApiModelProperty(value = "同行价：成人",example = "20.69")
    private String adult_trade_price;

    @ApiModelProperty(value = "同行价：儿童",example = "10.55")
    private String children_trade_price;

    @ApiModelProperty(value = "建议销售价：成人",example = "25.59")
    private String adult_sales_price;

    @ApiModelProperty(value = "建议销售价：儿童",example = "15")
    private String children_sales_price;

    @ApiModelProperty(value = "单房差价格",example = "200.50")
    private String room_diff_price;

    @ApiModelProperty(value = "库存数量",example = "20")
    private Integer inventory;

    @ApiModelProperty(value = "采购成本价",example = "10.88")
    private String cost_price;


    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getAdult_trade_price() {
        return adult_trade_price;
    }

    public void setAdult_trade_price(String adult_trade_price) {
        this.adult_trade_price = adult_trade_price;
    }

    public String getChildren_trade_price() {
        return children_trade_price;
    }

    public void setChildren_trade_price(String children_trade_price) {
        this.children_trade_price = children_trade_price;
    }

    public String getAdult_sales_price() {
        return adult_sales_price;
    }

    public void setAdult_sales_price(String adult_sales_price) {
        this.adult_sales_price = adult_sales_price;
    }

    public String getChildren_sales_price() {
        return children_sales_price;
    }

    public void setChildren_sales_price(String children_sales_price) {
        this.children_sales_price = children_sales_price;
    }

    public String getRoom_diff_price() {
        return room_diff_price;
    }

    public void setRoom_diff_price(String room_diff_price) {
        this.room_diff_price = room_diff_price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }



    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }
}
