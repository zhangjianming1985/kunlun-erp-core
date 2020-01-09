package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName RoutePlanNodeDto
 * @Description 线路行程安排节点
 * @Author Jm.zhang
 * @Date 2019/12/7 17:50
 * @Version 1.0
 **/
@ApiModel(description = "线路行程安排节点")
public class RoutePlanNodeDto {
    @ApiModelProperty(value = "节点编号,创建时 无需传参",example = "9000001939426040")
    private String node_code;

    @ApiModelProperty(required = true,value = "第几天",example = "1")
    @NotNull(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_DAY_INVALID)
    private Integer node_day;


    @ApiModelProperty(required = true,value = "交通工具：0=自理、1=飞机、2=火车、3=汽车、4=轮船、5=动车、6=高铁、7=快艇",example = "1")
    @NotNull(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_TRAFFIC_TYPE_INVALID)
    private Integer traffic_type;


    @ApiModelProperty(value = "交通工具说明，创建时 无需传参")
    private String traffic_type_str;

    /**
     * 途径地点区域信息
     */
    @ApiModelProperty(value = "途径地点",example = "北京、上海等")
    private String locale_area;

    @ApiModelProperty(value = "行程描述",example = "孔庙 游世界建筑史上的“孤例”，世界文化遗产——【孔庙】孔庙始建于南宋嘉定十二年")
    private String description;

    @ApiModelProperty(required = true,value = "是否含早餐 0=是、1=否",example = "1")
    @NotNull(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_HAS_BREAKFAST_INVALID)
    private Integer has_breakfast;

    @ApiModelProperty(required = true,value = "是否含午餐 0=是、1=否",example = "1")
    @NotNull(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_HAS_LUNCH_INVALID)
    private Integer has_lunch;

    @ApiModelProperty(required = true,value = "是否含晚餐 0=是、1=否",example = "1")
    @NotNull(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_HAS_DINNER_INVALID)
    private Integer has_dinner;

    @ApiModelProperty(value = "住宿",example = "住宿")
    private String hotel_description;

    public Integer getNode_day() {
        return node_day;
    }

    public void setNode_day(Integer node_day) {
        this.node_day = node_day;
    }

    public Integer getTraffic_type() {
        return traffic_type;
    }

    public void setTraffic_type(Integer traffic_type) {
        this.traffic_type = traffic_type;
        this.setTraffic_type_str(SysConstant.TrafficType.getTrafficType(this.traffic_type).getName());
    }

    public String getLocale_area() {
        return locale_area;
    }

    public void setLocale_area(String locale_area) {
        this.locale_area = locale_area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHas_breakfast() {
        return has_breakfast;
    }

    public void setHas_breakfast(Integer has_breakfast) {
        this.has_breakfast = has_breakfast;
    }

    public Integer getHas_lunch() {
        return has_lunch;
    }

    public void setHas_lunch(Integer has_lunch) {
        this.has_lunch = has_lunch;
    }

    public Integer getHas_dinner() {
        return has_dinner;
    }

    public void setHas_dinner(Integer has_dinner) {
        this.has_dinner = has_dinner;
    }

    public String getHotel_description() {
        return hotel_description;
    }

    public void setHotel_description(String hotel_description) {
        this.hotel_description = hotel_description;
    }

    public String getNode_code() {
        return node_code;
    }

    public void setNode_code(String node_code) {
        this.node_code = node_code;
    }

    public String getTraffic_type_str() {
        return traffic_type_str;
    }

    public void setTraffic_type_str(String traffic_type_str) {
        this.traffic_type_str = traffic_type_str;
    }
}
