package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteTrafficDto
 * @Description 线路团关联交通票务数据
 * @Author Jm.zhang
 * @Date 2019-12-24 22:58
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联交通票务数据")
public class RouteTrafficDto {
    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String traffic_code;

    @ApiModelProperty(required = true,value = "供应商编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(value = "供应商名称,新增时 无需传参",example = "兴旺航空公司")
    private String company_name;

    @ApiModelProperty(required = true,value = "交通工具：0=自理、1=飞机、2=火车、3=汽车、4=轮船、5=动车、6=高铁、7=快艇",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_TRAFFIC_TYPE_INVALID)
    private Integer traffic_type;

    @ApiModelProperty(value = "交通工具说明，创建时 无需传参",example = "飞机")
    private String traffic_type_str;

    @ApiModelProperty(required = true,value = "出发日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.ROUTE_TRAFFIC_DEPARTURE_DATE_INVALID)
    private String departure_date;

    @ApiModelProperty(required = true,value = "始发地",example = "杭州")
    @NotBlank(message = ErrorCodeConstant.ROUTE_TRAFFIC_DEPARTURE_INVALID)
    private String departure;

    @ApiModelProperty(required = true,value = "目的地",example = "海南")
    @NotBlank(message = ErrorCodeConstant.ROUTE_TRAFFIC_DESTINATION_INVALID)
    private String destination;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotBlank(message = ErrorCodeConstant.ROUTE_TRAFFIC_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "10.50")
    @NotBlank(message = ErrorCodeConstant.ROUTE_TRAFFIC_FEE_INVALID)
    private String fee;

    @ApiModelProperty(required = true,value = "数量",example = "5")
    @NotNull(message = ErrorCodeConstant.ROUTE_TRAFFIC_COUNT_INVALID)
    private Integer traffic_count;

    @ApiModelProperty(required = true,value = "总价",example = "105.00")
    @NotBlank(message = ErrorCodeConstant.ROUTE_TRAFFIC_TOTAL_FEE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getTraffic_code() {
        return traffic_code;
    }

    public void setTraffic_code(String traffic_code) {
        this.traffic_code = traffic_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getTraffic_type() {
        return traffic_type;
    }

    public void setTraffic_type(Integer traffic_type) {
        this.traffic_type = traffic_type;
        this.setTraffic_type_str(SysConstant.TrafficType.getTrafficType(this.traffic_type).getName());
    }

    public String getTraffic_type_str() {
        return traffic_type_str;
    }

    public void setTraffic_type_str(String traffic_type_str) {
        this.traffic_type_str = traffic_type_str;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getTraffic_count() {
        return traffic_count;
    }

    public void setTraffic_count(Integer traffic_count) {
        this.traffic_count = traffic_count;
    }

    public String getFee_total() {
        return fee_total;
    }

    public Double getFee_total_double() {
        return new BigDecimal(fee_total).doubleValue();
    }

    public void setFee_total(String fee_total) {
        this.fee_total = fee_total;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
