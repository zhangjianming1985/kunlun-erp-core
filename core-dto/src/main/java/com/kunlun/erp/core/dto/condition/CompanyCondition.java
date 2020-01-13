package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName CompanyCondition
 * @Description 公司检索条件
 * @Author Jm.zhang
 * @Date 2019/11/26 14:28
 * @Version 1.0
 **/
public class CompanyCondition extends BaseCondition {
    /**
     *
     * 企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商
     */
    private Integer company_type;
    /**
     * 企业类型集合(应用于 模糊检索)
     */
    private List<Integer> company_type_list;
    /**
     * 企业编号，根据金融账号、联系人信息 反查出企业编号
     */
    private List<String> company_code_list;
    /**
     * 企业名称
     */
    private String company_name;
    /**
     * 负责人姓名
     */
    private String leader_name;

    /**
     * 负责人手机
     */
    private String leader_mobile;

    /**
     * 共有”A级、B级、C级、D级“四个级别，默认D级；
     */
    private String credit_level;
    /**
     * 联系人姓名
     */
    private String person_name;

    /**
     * 联系人手机号码
     */
    private String person_mobile;

    /**
     * 线上销售渠道所属平台
     */
    private Integer belong_platform;

    private Integer uid;


    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public List<String> getCompany_code_list() {
        return company_code_list;
    }

    public void setCompany_code_list(List<String> company_code_list) {
        this.company_code_list = company_code_list;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getLeader_mobile() {
        return leader_mobile;
    }

    public void setLeader_mobile(String leader_mobile) {
        this.leader_mobile = leader_mobile;
    }

    public String getCredit_level() {
        return credit_level;
    }

    public void setCredit_level(String credit_level) {
        this.credit_level = credit_level;
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

    public Integer getBelong_platform() {
        return belong_platform;
    }

    public void setBelong_platform(Integer belong_platform) {
        this.belong_platform = belong_platform;
    }

    public List<Integer> getCompany_type_list() {
        return company_type_list;
    }

    public void setCompany_type_list(List<Integer> company_type_list) {
        this.company_type_list = company_type_list;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
