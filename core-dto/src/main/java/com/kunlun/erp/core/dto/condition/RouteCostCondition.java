package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName RouteCostCondition
 * @Description 成本明细条件
 * @Author Jm.zhang
 * @Date 2019/12/17 17:33
 * @Version 1.0
 **/
public class RouteCostCondition {
    /**
     * 日期
     */
    private List<String> departure_date;

    /**
     * 价格套餐编号
     */
    private String price_plan_code;

    public List<String> getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(List<String> departure_date) {
        this.departure_date = departure_date;
    }

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }
}
