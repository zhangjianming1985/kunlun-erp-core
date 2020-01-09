package com.kunlun.erp.core.dto.condition;

/**
 * @ClassName RoutePriceCondition
 * @Description 线路产品价格条件
 * @Author Jm.zhang
 * @Date 2019/12/17 16:44
 * @Version 1.0
 **/
public class RoutePriceCondition {
    /**
     * 日期
     */
    private String departure_date;
    /**
     * 价格方案编号
     */
    private String price_plan_code;

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }
}
