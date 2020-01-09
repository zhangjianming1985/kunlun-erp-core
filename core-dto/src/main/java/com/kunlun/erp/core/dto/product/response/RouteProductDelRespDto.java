package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteProductDelRespDto
 * @Description 线路产品删除响应
 * @Author Jm.zhang
 * @Date 2019/12/31 16:53
 * @Version 1.0
 **/
@ApiModel(description = "线路产品删除响应")
public class RouteProductDelRespDto {
    @ApiModelProperty(value = "删除的产品数量",example = "10")
    private int product_count;

    @ApiModelProperty(value = "删除的线路数量",example = "10")
    private int route_count;

    @ApiModelProperty(value = "删除的行程方案数量",example = "10")
    private int route_plan_count;

    @ApiModelProperty(value = "删除的行程节点数量",example = "10")
    private int plan_node_count;

    @ApiModelProperty(value = "价格日历数量",example = "10")
    private int calendar_count;

    @ApiModelProperty(value = "删除的价格套餐数量",example = "10")
    private int price_plan_count;

    @ApiModelProperty(value = "删除的价格套餐成本数量",example = "10")
    private int price_plan_cost_count;

    @ApiModelProperty(value = "删除行程方案的基本价格",example = "10")
    private int route_plan_base_price_count;

    @ApiModelProperty(value = "行程方案节点数量",example = "10")
    private int node_count;

    @ApiModelProperty(value = "删除的出游人数量",example = "10")
    public int client_count;

    @ApiModelProperty(value = "删除的团款数据数量",example = "10")
    public int income_count;

    @ApiModelProperty(value = "删除的订单数量",example = "1")
    public int order_count;


    @ApiModelProperty(value = "已删除的大厅产品数量",example = "7")
    private int hall_count;


    @ApiModelProperty(value = "关联导服数量",example = "5")
    private int guides_count;

    @ApiModelProperty(value = "关联交通票务数量",example = "5")
    private  int traffic_count;

    @ApiModelProperty(value = "关联酒店住宿数量",example = "5")
    private  int reside_count;

    @ApiModelProperty(value = "关联景点门票数量",example = "5")
    private int ticket_count;

    @ApiModelProperty(value = "关联餐饮数量",example = "5")
    private int meal_count;

    @ApiModelProperty(value = "关联车队数量",example = "5")
    private int motorcade_count;

    @ApiModelProperty(value = "关联保险数量",example = "5")
    private int insurance_count;

    @ApiModelProperty(value = "关联地接旅游公司团款数量",example = "5")
    private int travel_income_count;

    @ApiModelProperty(value = "关联地接旅游公司数量",example = "5")
    private int travel_count;

    @ApiModelProperty(value = "关联其他供应商数量",example = "5")
    private int other_count;

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public int getRoute_count() {
        return route_count;
    }

    public void setRoute_count(int route_count) {
        this.route_count = route_count;
    }

    public int getRoute_plan_count() {
        return route_plan_count;
    }

    public void setRoute_plan_count(int route_plan_count) {
        this.route_plan_count = route_plan_count;
    }

    public int getPlan_node_count() {
        return plan_node_count;
    }

    public void setPlan_node_count(int plan_node_count) {
        this.plan_node_count = plan_node_count;
    }

    public int getCalendar_count() {
        return calendar_count;
    }

    public void setCalendar_count(int calendar_count) {
        this.calendar_count = calendar_count;
    }

    public int getPrice_plan_count() {
        return price_plan_count;
    }

    public void setPrice_plan_count(int price_plan_count) {
        this.price_plan_count = price_plan_count;
    }

    public int getClient_count() {
        return client_count;
    }

    public void setClient_count(int client_count) {
        this.client_count = client_count;
    }

    public int getIncome_count() {
        return income_count;
    }

    public void setIncome_count(int income_count) {
        this.income_count = income_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getHall_count() {
        return hall_count;
    }

    public void setHall_count(int hall_count) {
        this.hall_count = hall_count;
    }

    public int getGuides_count() {
        return guides_count;
    }

    public void setGuides_count(int guides_count) {
        this.guides_count = guides_count;
    }

    public int getTraffic_count() {
        return traffic_count;
    }

    public void setTraffic_count(int traffic_count) {
        this.traffic_count = traffic_count;
    }

    public int getReside_count() {
        return reside_count;
    }

    public void setReside_count(int reside_count) {
        this.reside_count = reside_count;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public int getMeal_count() {
        return meal_count;
    }

    public void setMeal_count(int meal_count) {
        this.meal_count = meal_count;
    }

    public int getMotorcade_count() {
        return motorcade_count;
    }

    public void setMotorcade_count(int motorcade_count) {
        this.motorcade_count = motorcade_count;
    }

    public int getInsurance_count() {
        return insurance_count;
    }

    public void setInsurance_count(int insurance_count) {
        this.insurance_count = insurance_count;
    }

    public int getTravel_income_count() {
        return travel_income_count;
    }

    public void setTravel_income_count(int travel_income_count) {
        this.travel_income_count = travel_income_count;
    }

    public int getTravel_count() {
        return travel_count;
    }

    public void setTravel_count(int travel_count) {
        this.travel_count = travel_count;
    }

    public int getOther_count() {
        return other_count;
    }

    public void setOther_count(int other_count) {
        this.other_count = other_count;
    }

    public int getPrice_plan_cost_count() {
        return price_plan_cost_count;
    }

    public void setPrice_plan_cost_count(int price_plan_cost_count) {
        this.price_plan_cost_count = price_plan_cost_count;
    }

    public int getRoute_plan_base_price_count() {
        return route_plan_base_price_count;
    }

    public void setRoute_plan_base_price_count(int route_plan_base_price_count) {
        this.route_plan_base_price_count = route_plan_base_price_count;
    }

    public int getNode_count() {
        return node_count;
    }

    public void setNode_count(int node_count) {
        this.node_count = node_count;
    }
}
