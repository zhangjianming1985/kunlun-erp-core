package com.kunlun.erp.core.dto.routeHall;

/**
 * @ClassName PricePlanDeleteNotifyDto
 * @Description 价格套餐删除通知
 * @Author Jm.zhang
 * @Date 2019/12/18 19:35
 * @Version 1.0
 **/
public class PricePlanDeleteNotifyDto {
    /**
     * 价格方案编号
     */
    private String price_plan_code;

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }
}
