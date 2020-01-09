package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName SalesChannelCostCondition
 * @Description 销售渠道费用检索条件
 * @Author Jm.zhang
 * @Date 2019/12/4 18:42
 * @Version 1.0
 **/
public class SalesChannelCostCondition extends BaseCondition {

    /**
     * 企业编号
     */
    private String company_code;

    /**
     * 是否包含code
     */
    private Boolean cost_code_include;

    /**
     * 费用数据编号集合
     */
    private List<String> cost_codes;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Boolean getCost_code_include() {
        return cost_code_include;
    }

    public void setCost_code_include(Boolean cost_code_include) {
        this.cost_code_include = cost_code_include;
    }

    public List<String> getCost_codes() {
        return cost_codes;
    }

    public void setCost_codes(List<String> cost_codes) {
        this.cost_codes = cost_codes;
    }
}
