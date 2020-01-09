package com.kunlun.erp.core.dto.routeOrder.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOrderListReqDto
 * @Description 线路订单列表查询
 * @Author Jm.zhang
 * @Date 2019-12-23 23:44
 * @Version 1.0
 **/
@ApiModel(description = "线路订单列表查询")
public class RouteOrderListReqDto {
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

    @ApiModelProperty(value = "销售渠道名称",example = "天王渠道")
    private String company_name;

    @ApiModelProperty(value = "负责人名字",example = "1000001610779011")
    private String leader_name;

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

    @ApiModelProperty(value = "报名日期开始",example ="2019-12-10" )
    private String create_date_start;

    @ApiModelProperty(value = "报名日期结束",example ="2019-12-11" )
    private String create_date_end;

    @ApiModelProperty(value = "状态 0=确认、1=站位、2=取消",example = "1")
    private Integer state;

    @ApiModelProperty(value = "订单编号",example = "123")
    private String order_code;

    @ApiModelProperty(value = "渠道内部单号",example = "55555555555")
    private String company_order_code;

    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    private String group_code;

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



    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
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

    public String getCreate_date_start() {
        return create_date_start;
    }

    public void setCreate_date_start(String create_date_start) {
        this.create_date_start = create_date_start;
    }

    public String getCreate_date_end() {
        return create_date_end;
    }

    public void setCreate_date_end(String create_date_end) {
        this.create_date_end = create_date_end;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}
