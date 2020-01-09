package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteHallDto
 * @Description 线路大厅数据
 * @Author Jm.zhang
 * @Date 2019/12/18 20:23
 * @Version 1.0
 **/
@ApiModel(description = "线路大厅产品数据")
public class RouteHallDto {
    @ApiModelProperty(value = "团号",example ="13000001546253086" )
    private String group_code;

    @ApiModelProperty(value = "发团日期",example ="2019-12-12" )
    private String departure_date;

    @ApiModelProperty(value = "散团日期",example ="2019-12-20" )
    private String disband_date;

    @ApiModelProperty(value = "产品名称",example ="阿拉善豪华游" )
    private String product_name;

    @ApiModelProperty(value = "计划人数",example ="50" )
    private Integer count_plan;

    @ApiModelProperty(value = "确认人数",example ="30" )
    private Integer count_confirm;

    @ApiModelProperty(value = "站位人数",example ="10" )
    private Integer count_hold;

    @ApiModelProperty(value = "剩余人数",example ="10" )
    private Integer count_remain;

    @ApiModelProperty(value = "状态码，0=待出团、1=行程中、2=行程结束、3=行程取消、4=删除",example ="0" )
    private Integer status;

    @ApiModelProperty(value = "状态描述",example ="行程中" )
    private String status_str;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDisband_date() {
        return disband_date;
    }

    public void setDisband_date(String disband_date) {
        this.disband_date = disband_date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getCount_plan() {
        return count_plan;
    }

    public void setCount_plan(Integer count_plan) {
        this.count_plan = count_plan;
    }

    public Integer getCount_confirm() {
        return count_confirm;
    }

    public void setCount_confirm(Integer count_confirm) {
        this.count_confirm = count_confirm;
    }

    public Integer getCount_hold() {
        return count_hold;
    }

    public void setCount_hold(Integer count_hold) {
        this.count_hold = count_hold;
    }

    public Integer getCount_remain() {
        return count_remain;
    }

    public void setCount_remain(Integer count_remain) {
        this.count_remain = count_remain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatus_str() {
        this.setStatus_str(SysConstant.HallProductStatus.getHallProductStatus(this.status).getName());
        return status_str;
    }

    public void setStatus_str(String status_str) {
        this.status_str = status_str;
    }
}
