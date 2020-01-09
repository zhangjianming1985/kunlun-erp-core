package com.kunlun.erp.core.dto.routeHall;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RoomCount
 * @Description 用房统计
 * @Author Jm.zhang
 * @Date 2019-12-24 1:37
 * @Version 1.0
 **/
public class RoomCount {
    //0,1,2,
    @ApiModelProperty(value = "用房标准：0=准三星、1=挂三星、2=准四星、3=挂四星、4=准五星、5=挂五星、6其他",example = "0")
    private String hotel_level;

    //准三星,挂三星 逗号分隔
    private String hotel_level_str;

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

    public String getHotel_level_str() {
        return hotel_level_str;
    }

    public void setHotel_level_str(String hotel_level_str) {
        this.hotel_level_str = hotel_level_str;
    }
}
