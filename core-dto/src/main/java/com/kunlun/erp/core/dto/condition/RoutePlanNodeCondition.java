package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName RoutePlanNodeCondition
 * @Description 线路计划节点条件
 * @Author Jm.zhang
 * @Date 2019/12/10 19:22
 * @Version 1.0
 **/
public class RoutePlanNodeCondition {
    /**
     * 是否包含节点编号
     */
    private Boolean node_code_include;

    /**
     * 节点编号集合
     */
    private List<String> node_codes;


    /**
     * 是否包含线路行程计划编号
     */
    private Boolean plan_code_include;
    /**
     * 线路行程计划编号
     */
    private List<String> plan_codes;

    public Boolean getNode_code_include() {
        return node_code_include;
    }

    public void setNode_code_include(Boolean node_code_include) {
        this.node_code_include = node_code_include;
    }

    public List<String> getNode_codes() {
        return node_codes;
    }

    public void setNode_codes(List<String> node_codes) {
        this.node_codes = node_codes;
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
}
