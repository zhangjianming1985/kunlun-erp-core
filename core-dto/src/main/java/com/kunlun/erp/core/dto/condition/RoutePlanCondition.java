package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName RoutePlanCondition
 * @Description 线路计划条件参数
 * @Author Jm.zhang
 * @Date 2019/12/10 19:10
 * @Version 1.0
 **/
public class RoutePlanCondition {
    /**
     * 是否包含code
     */
    private Boolean plan_code_include;

    /**
     * 计划编号集合
     */
    private List<String> plan_codes;


    /**
     * 是否包含产品编号
     */
    private Boolean product_code_include;
    /**
     * 产品编号
     */
    private List<String> product_codes;


    /**
     * 是否包含线路编号
     */
    private Boolean route_code_include;
    /**
     * 线路编号
     */
    private List<String> route_codes;

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

    public List<String> getProduct_codes() {
        return product_codes;
    }

    public void setProduct_codes(List<String> product_codes) {
        this.product_codes = product_codes;
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

    public Boolean getRoute_code_include() {
        return route_code_include;
    }

    public void setRoute_code_include(Boolean route_code_include) {
        this.route_code_include = route_code_include;
    }
}
