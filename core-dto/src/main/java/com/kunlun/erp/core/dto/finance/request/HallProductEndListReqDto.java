package com.kunlun.erp.core.dto.finance.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName HallProductEndListReqDto
 * @Description 请求发团审核数据
 * @Author Jm.zhang
 * @Date 2019-12-26 23:05
 * @Version 1.0
 **/
@ApiModel(description = "请求发团审核数据")
public class HallProductEndListReqDto  {
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(value = "线路ID",example = "6000001165987885")
    private String product_code;

    @ApiModelProperty(value = "线路名称",example = "东南亚")
    private String product_name;

    @ApiModelProperty(value = "创建人ID",example = "1")
    private Integer creator_id;

    //跨度大，不能直接作为条件，需要 convert condition。渠道表 模糊查 company_code -> 根据 company_code 从订单表查 group_code
    @ApiModelProperty(value = "销售渠道名称",example = "天王渠道")
    private String company_name;

    //跨度大，不能直接作为条件，需要 convert condition。渠道表 模糊查 company_code -> 根据 company_code 从订单表查 group_code
    @ApiModelProperty(value = "负责人名字",example = "1000001610779011")
    private String leader_name;

    //跨度大，不能直接作为条件，需要 convert condition。人员表 模糊查 person_code -> 根据 person_code 从订单表查 group_code
    @ApiModelProperty(value = "联系人名字",example = "小芳")
    private String contact_name;

    @ApiModelProperty(value = "发团日期开始",example ="2019-12-12" )
    private String departure_date_start;

    @ApiModelProperty(value = "发团日期结束",example ="2019-12-15" )
    private String departure_date_end;

    @ApiModelProperty(value = "出游人名字",example ="霆锋" )
    private String client_name;

    @ApiModelProperty(value = "出游人手机号",example ="15915328866" )
    private String client_mobile;

    @ApiModelProperty(value = "报名起始日期，即订单创建开始日期",example ="2019-12-15" )
    private String order_create_start_date;

    @ApiModelProperty(value = "报名结束日期，即订单创建结束日期",example ="2019-12-18" )
    private String order_create_end_date;


    @ApiModelProperty(value = "状态 0=确认、1=站位、2=取消",example = "1")
    private Integer order_state;

    @ApiModelProperty(value = "订单编号",example = "123")
    private String order_code;

    @ApiModelProperty(value = "渠道内部单号",example = "55555555555")
    private String company_order_code;

    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    private String group_code;



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

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
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

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getDeparture_date_start() {
        return departure_date_start;
    }

    public void setDeparture_date_start(String departure_date_start) {
        this.departure_date_start = departure_date_start;
    }

    public String getDeparture_date_end() {
        return departure_date_end;
    }

    public void setDeparture_date_end(String departure_date_end) {
        this.departure_date_end = departure_date_end;
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

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
    }
}
