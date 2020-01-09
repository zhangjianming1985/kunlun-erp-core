package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName FinancialAccountCondition
 * @Description 金融账户操作条件
 * @Author Jm.zhang
 * @Date 2019/12/2 16:40
 * @Version 1.0
 **/
public class FinancialAccountCondition {
    /**
     * 是否包含code
     */
    private Boolean account_code_include;

    /**
     * 账户编号集合
     */
    private List<String> account_codes;

    /**
     * 企业编号
     */
    private String company_code;

    public List<String> getAccount_codes() {
        return account_codes;
    }

    public void setAccount_codes(List<String> account_codes) {
        this.account_codes = account_codes;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Boolean getAccount_code_include() {
        return account_code_include;
    }

    public void setAccount_code_include(Boolean account_code_include) {
        this.account_code_include = account_code_include;
    }
}
