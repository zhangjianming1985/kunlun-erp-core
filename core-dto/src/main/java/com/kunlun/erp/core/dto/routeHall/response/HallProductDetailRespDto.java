package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.product.RouteProductDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName HallProductDetailRespDto
 * @Description 报名大厅产品详情
 * @Author Jm.zhang
 * @Date 2019/12/19 15:13
 * @Version 1.0
 **/
@ApiModel(description = "报名大厅产品详情")
public class HallProductDetailRespDto extends RouteProductDto {
    @ApiModelProperty(value = "团号",example ="13000001546253086" )
    private String group_code;

    @ApiModelProperty(value = "发团日期",example ="2019-12-12" )
    private String departure_date;

    @ApiModelProperty(value = "散团日期",example ="2019-12-20" )
    private String disband_date;

    @ApiModelProperty(value = "价格套餐",example ="默认套餐1" )
    private String price_plan_name;

    @ApiModelProperty(value = "逗号分隔字符串，用房标准：0=准三星、1=挂三星、2=准四星、3=挂四星、4=准五星、5=挂五星、6其他  ",example = "0,1,2")
    private String hotel_level;
    @ApiModelProperty(required = true,value = "标准房数量",example = "1")
    private Integer room_standard_count;

    @ApiModelProperty(required = true,value = "大床房数量",example = "1")
    private Integer room_big_count;

    @ApiModelProperty(required = true,value = "三人间数量",example = "1")
    private Integer room_three_count;

    @ApiModelProperty(required = true,value = "陪房数量",example = "1")
    private Integer accompany_room_count;

    @ApiModelProperty(required = true,value = "不用房数量",example = "1")
    private Integer no_room_count;

    @ApiModelProperty(value = "成人数量",example ="10" )
    private Integer adult_count=0;

    @ApiModelProperty(value = "儿童数量",example ="5" )
    private Integer children_count=0;

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

    public String getPrice_plan_name() {
        return price_plan_name;
    }

    public void setPrice_plan_name(String price_plan_name) {
        this.price_plan_name = price_plan_name;
    }

    public Integer getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(Integer adult_count) {
        this.adult_count = adult_count;
    }

    public Integer getChildren_count() {
        return children_count;
    }

    public void setChildren_count(Integer children_count) {
        this.children_count = children_count;
    }

    public Integer getRoom_standard_count() {
        return room_standard_count;
    }

    public void setRoom_standard_count(Integer room_standard_count) {
        this.room_standard_count = room_standard_count;
    }

    public Integer getRoom_big_count() {
        return room_big_count;
    }

    public void setRoom_big_count(Integer room_big_count) {
        this.room_big_count = room_big_count;
    }

    public Integer getRoom_three_count() {
        return room_three_count;
    }

    public void setRoom_three_count(Integer room_three_count) {
        this.room_three_count = room_three_count;
    }

    public Integer getAccompany_room_count() {
        return accompany_room_count;
    }

    public void setAccompany_room_count(Integer accompany_room_count) {
        this.accompany_room_count = accompany_room_count;
    }

    public Integer getNo_room_count() {
        return no_room_count;
    }

    public void setNo_room_count(Integer no_room_count) {
        this.no_room_count = no_room_count;
    }

    public String getHotel_level() {
        return hotel_level;
    }

    public void setHotel_level(String hotel_level) {
        this.hotel_level = hotel_level;
    }
}
