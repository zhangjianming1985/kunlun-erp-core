package com.kunlun.erp.core.dto.routeHall;

import java.util.List;

/**
 * @ClassName PriceDateDeleteNotifyDto
 * @Description 日期删除通知
 * @Author Jm.zhang
 * @Date 2019/12/18 19:44
 * @Version 1.0
 **/
public class PriceDateDeleteNotifyDto {
    /**
     * 价格套餐方案编号
     */
    private String price_plan_code;

    /**
     * 删除的日期
     */
    private List<String> date_info;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }

    public List<String> getDate_info() {
        return date_info;
    }

    public void setDate_info(List<String> date_info) {
        this.date_info = date_info;
    }
}
