package com.kunlun.erp.core.dto.company.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CompanyDeleteRespDto
 * @Description 数据删除结果
 * @Author Jm.zhang
 * @Date 2019/12/30 11:39
 * @Version 1.0
 **/
@ApiModel(description = "CompanyDeleteRespDto")
public class CompanyDeleteRespDto {
    @ApiModelProperty(value = "关联人员数量",example = "5")
    private int person_count;

    @ApiModelProperty(value = "关联金融账户数量",example = "5")
    private int financial_count;

    @ApiModelProperty(value = "关联销售渠道费用数量",example = "5")
    private int cost_count;

    @ApiModelProperty(value = "关联出游人数量",example = "5")
    private int client_count;

    @ApiModelProperty(value = "关联确认团款数量",example = "5")
    private  int income_count;

    @ApiModelProperty(value = "关联订单数量",example = "5")
    private int order_count;

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

    @ApiModelProperty(value = "企业数量",example = "5")
    private int company_count;

    public int getPerson_count() {
        return person_count;
    }

    public void setPerson_count(int person_count) {
        this.person_count = person_count;
    }

    public int getFinancial_count() {
        return financial_count;
    }

    public void setFinancial_count(int financial_count) {
        this.financial_count = financial_count;
    }

    public int getCost_count() {
        return cost_count;
    }

    public void setCost_count(int cost_count) {
        this.cost_count = cost_count;
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

    public int getCompany_count() {
        return company_count;
    }

    public void setCompany_count(int company_count) {
        this.company_count = company_count;
    }
}
