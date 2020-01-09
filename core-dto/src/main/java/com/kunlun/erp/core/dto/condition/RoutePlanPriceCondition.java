package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName RoutePlanPriceCondition
 * @Description 线路计划价格条件
 * @Author Jm.zhang
 * @Date 2019/12/10 19:33
 * @Version 1.0
 **/
public class RoutePlanPriceCondition {
    /**
     * 是否包含价格编号
     */
    private Boolean price_code_include;

    /**
     * 价格编号
     */
    private List<String> price_codes;


    /**
     * 是否包含线路行程计划编号
     */
    private Boolean plan_code_include;
    /**
     * 线路行程计划编号
     */
    private List<String> plan_codes;


    /**
     * 是否包含线路编号
     */
    private Boolean route_code_include;
    /**
     * 线路编号
     */
    private List<String> route_codes;


    /**
     * 是否包含产品编号
     */
    private Boolean product_code_include;
    /**
     * 产品编号
     */
    private List<String> product_codes;


    public Boolean getPrice_code_include() {
        return price_code_include;
    }

    public void setPrice_code_include(Boolean price_code_include) {
        this.price_code_include = price_code_include;
    }

    public List<String> getPrice_codes() {
        return price_codes;
    }

    public void setPrice_codes(List<String> price_codes) {
        this.price_codes = price_codes;
    }

    public Boolean getPlan_code_include() {
        return plan_code_include;
    }

    public void setPlan_code_include(Boolean plan_code_include) {
        this.plan_code_include = plan_code_include;
    }

    public List<String> getPlan_codes() {
        return plan_codes;
    }

    public void setPlan_codes(List<String> plan_codes) {
        this.plan_codes = plan_codes;
    }

    public Boolean getRoute_code_include() {
        return route_code_include;
    }

    public void setRoute_code_include(Boolean route_code_include) {
        this.route_code_include = route_code_include;
    }

    public List<String> getRoute_codes() {
        return route_codes;
    }

    public void setRoute_codes(List<String> route_codes) {
        this.route_codes = route_codes;
    }

    public Boolean getProduct_code_include() {
        return product_code_include;
    }

    public void setProduct_code_include(Boolean product_code_include) {
        this.product_code_include = product_code_include;
    }

    public List<String> getProduct_codes() {
        return product_codes;
    }

    public void setProduct_codes(List<String> product_codes) {
        this.product_codes = product_codes;
    }
}
