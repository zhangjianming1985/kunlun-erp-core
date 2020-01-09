package com.kunlun.erp.core.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName PermissionKeyProperties
 * @Description 权限key
 * @Author Jm.zhang
 * @Date 2019/11/23 14:43
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "key")
@PropertySource(value = "permission-key.properties",encoding = "UTF-8")
public class PermissionKeyProperties {
    /**
     * 产品类别设置
     */
    private  String product_setting;

    /**
     * 线路产品列表
     */
    private String route_product_list;
    /**
     * 创建线路
     */
    private String create_route;

    /**
     * 编辑线路
     */
    private String edit_route;

    /**
     * 删除线路
     */
    private String delete_route;
    /**
     * 报名大厅
     */
    private String route_daily_list;
    /**
     * 编辑报名大厅产品
     */
    private String route_daily_edit;
    /**
     * 报名线路订单
     */
    private String  route_order_create;

    /**
     * 线路订单列表
     */
    private String route_order_list;
    /**
     * 线路订单编辑
     *
     */
    private String route_order_edit;

    /**
     * 线路订单删除
     *
     */
    private String route_order_delete;

    /**
     * 销售渠道列表
     */
    private String online_ot;
    /**
     * 新建销售渠道
     */
    private String create_online_ot;
    /**
     * 编辑销售渠道
     */
    private String edit_online_ot;
    /**
     * 删除销售渠道
     */
    private String delete_online_ot;



    /**
     * 线下门店列表
     */
    private String offline_store;
    /**
     * 新建线下门店
     */
    private String create_offline_store;
    /**
     * 编辑线下门店
     */
    private String edit_offline_store;
    /**
     * 删除线下门店
     */
    private String delete_offline_store;

    /**
     * 其他销售渠道列表
     */
    private String other_sales_channel;
    /**
     * 新建其他销售渠道
     */
    private String create_other_sales_channel;
    /**
     * 编辑其他销售渠道
     */
    private String edit_other_sales_channel;
    /**
     * 删除其他销售渠道
     */
    private String delete_other_sales_channel;


    /**
     * 旅行社列表
     */
    private String travel_agency_list;
    /**
     * 新建旅行社
     */
    private String create_travel_agency;
    /**
     * 编辑旅行社
     */
    private String edit_travel_agency;
    /**
     * 删除旅行社
     */
    private String delete_travel_agency;


    /**
     * 车队列表
     */
    private String car_team_list;
    /**
     * 新建车队
     */
    private String create_car_team;
    /**
     * 编辑车队
     */
    private String edit_car_team;
    /**
     * 删除车队
     */
    private String delete_car_team;


    /**
     * 酒店住宿列表
     */
    private String reside_list;
    /**
     * 新建酒店住宿
     */
    private String create_reside;
    /**
     * 编辑酒店住宿
     */
    private String edit_reside;
    /**
     * 删除酒店住宿
     */
    private String delete_reside;


    /**
     * 餐饮列表
     */
    private String meal_list;
    /**
     * 新建餐饮
     */
    private String create_meal;
    /**
     * 编辑餐饮
     */
    private String edit_meal;
    /**
     * 删除餐饮
     */
    private String delete_meal;


    /**
     * 景区景点列表
     */
    private String ticket_list;
    /**
     * 新建景区景点
     */
    private String create_ticket;
    /**
     * 编辑景区景点
     */
    private String edit_ticket;
    /**
     * 删除景区景点
     */
    private String delete_ticket;

    /**
     * 交通票务列表
     */
    private String traffic_list;
    /**
     * 新建交通票务
     */
    private String create_traffic;
    /**
     * 编辑交通票务
     */
    private String edit_traffic;
    /**
     * 删除交通票务
     */
    private String delete_traffic;


    /**
     * 保险列表
     */
    private String insurance_list;
    /**
     * 新建保险列表
     */
    private String create_insurance;
    /**
     * 编辑保险列表
     */
    private String edit_insurance;
    /**
     * 删除保险列表
     */
    private String delete_insurance;



    /**
     * 导服列表
     */
    private String guides_list;
    /**
     * 新建导服
     */
    private String create_guides;
    /**
     * 编辑导服
     */
    private String edit_guides;
    /**
     * 删除导服
     */
    private String delete_guides;


    /**
     * 其他供应商列表
     */
    private String other_supplier_list;
    /**
     * 新建其他供应商
     */
    private String create_other_supplier;
    /**
     * 编辑其他供应商
     */
    private String edit_other_supplier;
    /**
     * 删除其他供应商
     */
    private String delete_other_supplier;

    /**
     * 发团审核
     */
    private String Finance_FaTuan_Audit;
    /**
     *审核
     */
    private String Finance_Audit;

    /**
     * 应收款列表
     */
    private String Finance_Receivables_List;
    /**
     * 应付款列表
     */
    private String Finance_AccountsPayable_List;

    public String getCreate_travel_agency() {
        return create_travel_agency;
    }

    public void setCreate_travel_agency(String create_travel_agency) {
        this.create_travel_agency = create_travel_agency;
    }

    public String getEdit_travel_agency() {
        return edit_travel_agency;
    }

    public void setEdit_travel_agency(String edit_travel_agency) {
        this.edit_travel_agency = edit_travel_agency;
    }

    public String getDelete_travel_agency() {
        return delete_travel_agency;
    }

    public void setDelete_travel_agency(String delete_travel_agency) {
        this.delete_travel_agency = delete_travel_agency;
    }

    public String getTravel_agency_list() {
        return travel_agency_list;
    }

    public void setTravel_agency_list(String travel_agency_list) {
        this.travel_agency_list = travel_agency_list;
    }

    public String getOnline_ot() {
        return online_ot;
    }

    public void setOnline_ot(String online_ot) {
        this.online_ot = online_ot;
    }

    public String getCreate_online_ot() {
        return create_online_ot;
    }

    public void setCreate_online_ot(String create_online_ot) {
        this.create_online_ot = create_online_ot;
    }

    public String getEdit_online_ot() {
        return edit_online_ot;
    }

    public void setEdit_online_ot(String edit_online_ot) {
        this.edit_online_ot = edit_online_ot;
    }

    public String getDelete_online_ot() {
        return delete_online_ot;
    }

    public void setDelete_online_ot(String delete_online_ot) {
        this.delete_online_ot = delete_online_ot;
    }

    public String getOffline_store() {
        return offline_store;
    }

    public void setOffline_store(String offline_store) {
        this.offline_store = offline_store;
    }

    public String getCreate_offline_store() {
        return create_offline_store;
    }

    public void setCreate_offline_store(String create_offline_store) {
        this.create_offline_store = create_offline_store;
    }

    public String getEdit_offline_store() {
        return edit_offline_store;
    }

    public void setEdit_offline_store(String edit_offline_store) {
        this.edit_offline_store = edit_offline_store;
    }

    public String getDelete_offline_store() {
        return delete_offline_store;
    }

    public void setDelete_offline_store(String delete_offline_store) {
        this.delete_offline_store = delete_offline_store;
    }

    public String getOther_sales_channel() {
        return other_sales_channel;
    }

    public void setOther_sales_channel(String other_sales_channel) {
        this.other_sales_channel = other_sales_channel;
    }

    public String getCreate_other_sales_channel() {
        return create_other_sales_channel;
    }

    public void setCreate_other_sales_channel(String create_other_sales_channel) {
        this.create_other_sales_channel = create_other_sales_channel;
    }

    public String getEdit_other_sales_channel() {
        return edit_other_sales_channel;
    }

    public void setEdit_other_sales_channel(String edit_other_sales_channel) {
        this.edit_other_sales_channel = edit_other_sales_channel;
    }

    public String getDelete_other_sales_channel() {
        return delete_other_sales_channel;
    }

    public void setDelete_other_sales_channel(String delete_other_sales_channel) {
        this.delete_other_sales_channel = delete_other_sales_channel;
    }

    public String getCar_team_list() {
        return car_team_list;
    }

    public void setCar_team_list(String car_team_list) {
        this.car_team_list = car_team_list;
    }

    public String getCreate_car_team() {
        return create_car_team;
    }

    public void setCreate_car_team(String create_car_team) {
        this.create_car_team = create_car_team;
    }

    public String getEdit_car_team() {
        return edit_car_team;
    }

    public void setEdit_car_team(String edit_car_team) {
        this.edit_car_team = edit_car_team;
    }

    public String getDelete_car_team() {
        return delete_car_team;
    }

    public void setDelete_car_team(String delete_car_team) {
        this.delete_car_team = delete_car_team;
    }

    public String getReside_list() {
        return reside_list;
    }

    public void setReside_list(String reside_list) {
        this.reside_list = reside_list;
    }

    public String getCreate_reside() {
        return create_reside;
    }

    public void setCreate_reside(String create_reside) {
        this.create_reside = create_reside;
    }

    public String getEdit_reside() {
        return edit_reside;
    }

    public void setEdit_reside(String edit_reside) {
        this.edit_reside = edit_reside;
    }

    public String getDelete_reside() {
        return delete_reside;
    }

    public void setDelete_reside(String delete_reside) {
        this.delete_reside = delete_reside;
    }

    public String getMeal_list() {
        return meal_list;
    }

    public void setMeal_list(String meal_list) {
        this.meal_list = meal_list;
    }

    public String getCreate_meal() {
        return create_meal;
    }

    public void setCreate_meal(String create_meal) {
        this.create_meal = create_meal;
    }

    public String getEdit_meal() {
        return edit_meal;
    }

    public void setEdit_meal(String edit_meal) {
        this.edit_meal = edit_meal;
    }

    public String getDelete_meal() {
        return delete_meal;
    }

    public void setDelete_meal(String delete_meal) {
        this.delete_meal = delete_meal;
    }

    public String getTicket_list() {
        return ticket_list;
    }

    public void setTicket_list(String ticket_list) {
        this.ticket_list = ticket_list;
    }

    public String getCreate_ticket() {
        return create_ticket;
    }

    public void setCreate_ticket(String create_ticket) {
        this.create_ticket = create_ticket;
    }

    public String getEdit_ticket() {
        return edit_ticket;
    }

    public void setEdit_ticket(String edit_ticket) {
        this.edit_ticket = edit_ticket;
    }

    public String getDelete_ticket() {
        return delete_ticket;
    }

    public void setDelete_ticket(String delete_ticket) {
        this.delete_ticket = delete_ticket;
    }

    public String getTraffic_list() {
        return traffic_list;
    }

    public void setTraffic_list(String traffic_list) {
        this.traffic_list = traffic_list;
    }

    public String getCreate_traffic() {
        return create_traffic;
    }

    public void setCreate_traffic(String create_traffic) {
        this.create_traffic = create_traffic;
    }

    public String getEdit_traffic() {
        return edit_traffic;
    }

    public void setEdit_traffic(String edit_traffic) {
        this.edit_traffic = edit_traffic;
    }

    public String getDelete_traffic() {
        return delete_traffic;
    }

    public void setDelete_traffic(String delete_traffic) {
        this.delete_traffic = delete_traffic;
    }

    public String getInsurance_list() {
        return insurance_list;
    }

    public void setInsurance_list(String insurance_list) {
        this.insurance_list = insurance_list;
    }

    public String getCreate_insurance() {
        return create_insurance;
    }

    public void setCreate_insurance(String create_insurance) {
        this.create_insurance = create_insurance;
    }

    public String getEdit_insurance() {
        return edit_insurance;
    }

    public void setEdit_insurance(String edit_insurance) {
        this.edit_insurance = edit_insurance;
    }

    public String getDelete_insurance() {
        return delete_insurance;
    }

    public void setDelete_insurance(String delete_insurance) {
        this.delete_insurance = delete_insurance;
    }

    public String getGuides_list() {
        return guides_list;
    }

    public void setGuides_list(String guides_list) {
        this.guides_list = guides_list;
    }

    public String getCreate_guides() {
        return create_guides;
    }

    public void setCreate_guides(String create_guides) {
        this.create_guides = create_guides;
    }

    public String getEdit_guides() {
        return edit_guides;
    }

    public void setEdit_guides(String edit_guides) {
        this.edit_guides = edit_guides;
    }

    public String getDelete_guides() {
        return delete_guides;
    }

    public void setDelete_guides(String delete_guides) {
        this.delete_guides = delete_guides;
    }

    public String getOther_supplier_list() {
        return other_supplier_list;
    }

    public void setOther_supplier_list(String other_supplier_list) {
        this.other_supplier_list = other_supplier_list;
    }

    public String getCreate_other_supplier() {
        return create_other_supplier;
    }

    public void setCreate_other_supplier(String create_other_supplier) {
        this.create_other_supplier = create_other_supplier;
    }

    public String getEdit_other_supplier() {
        return edit_other_supplier;
    }

    public void setEdit_other_supplier(String edit_other_supplier) {
        this.edit_other_supplier = edit_other_supplier;
    }

    public String getDelete_other_supplier() {
        return delete_other_supplier;
    }

    public void setDelete_other_supplier(String delete_other_supplier) {
        this.delete_other_supplier = delete_other_supplier;
    }

    public String getProduct_setting() {
        return product_setting;
    }

    public void setProduct_setting(String product_setting) {
        this.product_setting = product_setting;
    }

    public String getRoute_product_list() {
        return route_product_list;
    }

    public void setRoute_product_list(String route_product_list) {
        this.route_product_list = route_product_list;
    }

    public String getCreate_route() {
        return create_route;
    }

    public void setCreate_route(String create_route) {
        this.create_route = create_route;
    }

    public String getEdit_route() {
        return edit_route;
    }

    public void setEdit_route(String edit_route) {
        this.edit_route = edit_route;
    }

    public String getRoute_daily_list() {
        return route_daily_list;
    }

    public void setRoute_daily_list(String route_daily_list) {
        this.route_daily_list = route_daily_list;
    }

    public String getRoute_daily_edit() {
        return route_daily_edit;
    }

    public void setRoute_daily_edit(String route_daily_edit) {
        this.route_daily_edit = route_daily_edit;
    }

    public String getRoute_order_create() {
        return route_order_create;
    }

    public void setRoute_order_create(String route_order_create) {
        this.route_order_create = route_order_create;
    }

    public String getRoute_order_edit() {
        return route_order_edit;
    }

    public void setRoute_order_edit(String route_order_edit) {
        this.route_order_edit = route_order_edit;
    }

    public String getRoute_order_list() {
        return route_order_list;
    }

    public void setRoute_order_list(String route_order_list) {
        this.route_order_list = route_order_list;
    }

    public String getFinance_FaTuan_Audit() {
        return Finance_FaTuan_Audit;
    }

    public void setFinance_FaTuan_Audit(String finance_FaTuan_Audit) {
        Finance_FaTuan_Audit = finance_FaTuan_Audit;
    }

    public String getFinance_Audit() {
        return Finance_Audit;
    }

    public void setFinance_Audit(String finance_Audit) {
        Finance_Audit = finance_Audit;
    }

    public String getFinance_Receivables_List() {
        return Finance_Receivables_List;
    }

    public void setFinance_Receivables_List(String finance_Receivables_List) {
        Finance_Receivables_List = finance_Receivables_List;
    }

    public String getFinance_AccountsPayable_List() {
        return Finance_AccountsPayable_List;
    }

    public void setFinance_AccountsPayable_List(String finance_AccountsPayable_List) {
        Finance_AccountsPayable_List = finance_AccountsPayable_List;
    }

    public String getRoute_order_delete() {
        return route_order_delete;
    }

    public void setRoute_order_delete(String route_order_delete) {
        this.route_order_delete = route_order_delete;
    }

    public String getDelete_route() {
        return delete_route;
    }

    public void setDelete_route(String delete_route) {
        this.delete_route = delete_route;
    }
}
