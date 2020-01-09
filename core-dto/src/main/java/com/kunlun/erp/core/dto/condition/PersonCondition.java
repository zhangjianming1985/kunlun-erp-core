package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName PersonCondition
 * @Description 人员操作条件
 * @Author Jm.zhang
 * @Date 2019/12/2 17:28
 * @Version 1.0
 **/
public class PersonCondition extends BaseCondition {
    /**
     * 是否包含code
     */
    private Boolean person_code_include;

    /**
     * 人员编号集合
     */
    private List<String> person_codes;

    /**
     * 企业编号
     */
    private String company_code;

    /**
     * 人员类型
     */
    private Integer person_type;

    /**
     * 是否模糊检索姓名
     */
    private Boolean person_name_like;

    /**
     * 姓名
     */
    private String person_name;

    /**
     * 手机号码
     */
    private String person_mobile;

    public Boolean getPerson_code_include() {
        return person_code_include;
    }

    public void setPerson_code_include(Boolean person_code_include) {
        this.person_code_include = person_code_include;
    }

    public List<String> getPerson_codes() {
        return person_codes;
    }

    public void setPerson_codes(List<String> person_codes) {
        this.person_codes = person_codes;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Integer getPerson_type() {
        return person_type;
    }

    public void setPerson_type(Integer person_type) {
        this.person_type = person_type;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }

    public Boolean getPerson_name_like() {
        return person_name_like;
    }

    public void setPerson_name_like(Boolean person_name_like) {
        this.person_name_like = person_name_like;
    }
}
