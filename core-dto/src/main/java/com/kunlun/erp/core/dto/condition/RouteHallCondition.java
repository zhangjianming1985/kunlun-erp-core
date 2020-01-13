package com.kunlun.erp.core.dto.condition;

import java.util.List;

/**
 * @ClassName RouteHallCondition
 * @Description 线路大厅数据检索
 * @Author Jm.zhang
 * @Date 2019/12/18 20:32
 * @Version 1.0
 **/
public class RouteHallCondition extends BaseCondition{
    /**
     * 线路ID
     */
    private String product_code;
    /**
     * 线路名称
     */
    private String product_name;

    /**
     * 创建人ID
     */
    private Integer creator_id;
    /**
     * 团号集合
     */
    private List<String> group_code_list;

    /**
     * 团号
     */
    private String group_code;


    /**
     * 出游人名字
     */
    private String client_name;

    /**
     * 出游人手机号
     */
    private String client_mobile;

    /**
     * 报名起始日期，即订单创建开始日期
     */
    private String order_create_start_date;

    /**
     * 报名结束日期，即订单创建结束日期
     */
    private String order_create_end_date;

    /**
     * 订单状态
     */
    private Integer order_state;

    /**
     * 订单编号
     */
    private String order_code;

    /**
     * 渠道内部单号
     */
    private String company_order_code;

    /**
     * 行程状态
     */
    private Integer status;

    /**
     * 行程状态集合
     */
    private List<Integer> not_include_status;

    /**
     * 内部团号
     */
    private String internal_code;

    /**
     * 发团开始日期
     */
    private String departure_start_date;

    /**
     * 发团结束日期
     */
    private String departure_end_date;

    /**
     * 散团开始日期
     */
    private String disband_start_date;

    /**
     * 散团结束日期
     */
    private String disband_end_date;
    /**
     * 产品类别编号
     */
    private String category_code;

    private Integer uid;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInternal_code() {
        return internal_code;
    }

    public void setInternal_code(String internal_code) {
        this.internal_code = internal_code;
    }

    public String getDeparture_start_date() {
        return departure_start_date;
    }

    public void setDeparture_start_date(String departure_start_date) {
        this.departure_start_date = departure_start_date;
    }

    public String getDeparture_end_date() {
        return departure_end_date;
    }

    public void setDeparture_end_date(String departure_end_date) {
        this.departure_end_date = departure_end_date;
    }

    public String getDisband_start_date() {
        return disband_start_date;
    }

    public void setDisband_start_date(String disband_start_date) {
        this.disband_start_date = disband_start_date;
    }

    public String getDisband_end_date() {
        return disband_end_date;
    }

    public void setDisband_end_date(String disband_end_date) {
        this.disband_end_date = disband_end_date;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public List<String> getGroup_code_list() {
        return group_code_list;
    }

    public void setGroup_code_list(List<String> group_code_list) {
        this.group_code_list = group_code_list;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_mobile() {
        return client_mobile;
    }

    public void setClient_mobile(String client_mobile) {
        this.client_mobile = client_mobile;
    }

    public String getOrder_create_start_date() {
        return order_create_start_date;
    }

    public void setOrder_create_start_date(String order_create_start_date) {
        this.order_create_start_date = order_create_start_date;
    }

    public String getOrder_create_end_date() {
        return order_create_end_date;
    }

    public void setOrder_create_end_date(String order_create_end_date) {
        this.order_create_end_date = order_create_end_date;
    }

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getCompany_order_code() {
        return company_order_code;
    }

    public void setCompany_order_code(String company_order_code) {
        this.company_order_code = company_order_code;
    }

    public List<Integer> getNot_include_status() {
        return not_include_status;
    }

    public void setNot_include_status(List<Integer> not_include_status) {
        this.not_include_status = not_include_status;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
