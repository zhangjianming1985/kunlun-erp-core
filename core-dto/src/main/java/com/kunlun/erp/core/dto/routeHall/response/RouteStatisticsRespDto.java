package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.*;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName RouteStatisticsRespDto
 * @Description 收支汇总响应
 * @Author Jm.zhang
 * @Date 2019/12/26 9:37
 * @Version 1.0
 **/
@ApiModel(description = "收支汇总响应")
public class RouteStatisticsRespDto {
    //导服汇总
    private List<RouteGuidesDto> guides_data;

    //交通票务汇总
    private List<RouteTrafficDto> traffic_data;

    //住宿
    private List<RouteResideDto> reside_data;

    //景点门票
    private List<RouteTicketDto> ticket_data;

    //用餐
    private List<RouteMealDto> meal_data;

    //用车
    private List<RouteMotorcadeDto> motorcade_data;

    //保险
    private List<RouteInsuranceDto> insurance_data;

    //其他
    private List<RouteOtherDto> other_data;

    //销售渠道
    private List<OrderIncomeDto> income_data;

    //地接
    private List<RouteTravelAgencyIncomeDto> travel_income_data;

    @ApiModelProperty(value = "总收入",example = "500.69")
    private String total_income;
    @ApiModelProperty(value = "总支出",example = "500.69")
    private String total_cost;

    public List<RouteGuidesDto> getGuides_data() {
        return guides_data;
    }

    public void setGuides_data(List<RouteGuidesDto> guides_data) {
        this.guides_data = guides_data;
    }

    public List<RouteTrafficDto> getTraffic_data() {
        return traffic_data;
    }

    public void setTraffic_data(List<RouteTrafficDto> traffic_data) {
        this.traffic_data = traffic_data;
    }

    public List<RouteResideDto> getReside_data() {
        return reside_data;
    }

    public void setReside_data(List<RouteResideDto> reside_data) {
        this.reside_data = reside_data;
    }

    public List<RouteTicketDto> getTicket_data() {
        return ticket_data;
    }

    public void setTicket_data(List<RouteTicketDto> ticket_data) {
        this.ticket_data = ticket_data;
    }

    public List<RouteMealDto> getMeal_data() {
        return meal_data;
    }

    public void setMeal_data(List<RouteMealDto> meal_data) {
        this.meal_data = meal_data;
    }

    public List<RouteMotorcadeDto> getMotorcade_data() {
        return motorcade_data;
    }

    public void setMotorcade_data(List<RouteMotorcadeDto> motorcade_data) {
        this.motorcade_data = motorcade_data;
    }

    public List<RouteInsuranceDto> getInsurance_data() {
        return insurance_data;
    }

    public void setInsurance_data(List<RouteInsuranceDto> insurance_data) {
        this.insurance_data = insurance_data;
    }

    public List<RouteOtherDto> getOther_data() {
        return other_data;
    }

    public void setOther_data(List<RouteOtherDto> other_data) {
        this.other_data = other_data;
    }

    public List<OrderIncomeDto> getIncome_data() {
        return income_data;
    }

    public void setIncome_data(List<OrderIncomeDto> income_data) {
        this.income_data = income_data;
    }

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public List<RouteTravelAgencyIncomeDto> getTravel_income_data() {
        return travel_income_data;
    }

    public void setTravel_income_data(List<RouteTravelAgencyIncomeDto> travel_income_data) {
        this.travel_income_data = travel_income_data;
    }
}
