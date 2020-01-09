package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName RoutePlanDto
 * @Description 线路计划数据（默认行程1  行程2 3 ...）
 * @Author Jm.zhang
 * @Date 2019/12/7 15:13
 * @Version 1.0
 **/
@ApiModel(description = "线路计划数据（默认行程1  行程2 3 ...）")
public class RoutePlanDto {

    @ApiModelProperty(value = "线路计划编号（行程1 行程2 ...）,创建时 无需传参")
    private String route_plan_code;

    @ApiModelProperty(required = true,value = "线路计划名称",example = "我的行程1")
    private String route_plan_name;

    @ApiModelProperty(required = true,value = "是否默认行程0=默认 1=不默认",example = "0")
    private Integer is_default;

    /**
     * 出发地区域信息
     */
    @NotNull(message = ErrorCodeConstant.AREA_DATA_NULL)
    @Valid
    private AreaDto departure_area_info;

    @ApiModelProperty(value = "出发地区释义",example = "国内/华南/广东/深圳/龙岗")
    private String departure_area_str;

    /**
     * 目的地区域信息
     */
    @NotNull(message = ErrorCodeConstant.AREA_DATA_NULL)
    @Valid
    private AreaDto destination_area_info;

    @ApiModelProperty(value = "目的地释义",example = "国内/华南/广东/深圳/龙岗")
    private String destination_area_str;
    /**
     * 成团地点区域信息
     */
    @NotNull(message = ErrorCodeConstant.AREA_DATA_NULL)
    @Valid
    private AreaDto rendezvous_area_info;

    @ApiModelProperty(value = "成团地点区释义",example = "国内/华南/广东/深圳/龙岗")
    private String rendezvous_area_str;

    @ApiModelProperty(required =true,value = "天：行程天数",example = "10")
    private Integer days;

    @ApiModelProperty(required = true,value = "晚：行程夜晚数量",example = "8")
    private Integer nights;
    /**
     * 行程节点
     */
    @NotEmpty(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_INVALID)
    @Valid
    private List<RoutePlanNodeDto> plan_node_info;

    /**
     * 价格明细
     */
    private List<RoutePlanBasePriceDto> plan_price_info;

    @ApiModelProperty(value = "费用包含内容",example = "包含吃喝玩乐")
    private String fee_contain;

    @ApiModelProperty(value = "费用不包含内容",example = "不包含黄赌毒")
    private String fee_not_contain;

    @ApiModelProperty(value = "预定须知",example = "预定须知")
    private String pre_notice;

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public AreaDto getDeparture_area_info() {
        return departure_area_info;
    }

    public void setDeparture_area_info(AreaDto departure_area_info) {
        this.departure_area_info = departure_area_info;
    }

    public AreaDto getDestination_area_info() {
        return destination_area_info;
    }

    public void setDestination_area_info(AreaDto destination_area_info) {
        this.destination_area_info = destination_area_info;
    }

    public AreaDto getRendezvous_area_info() {
        return rendezvous_area_info;
    }

    public void setRendezvous_area_info(AreaDto rendezvous_area_info) {
        this.rendezvous_area_info = rendezvous_area_info;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public String getFee_contain() {
        return fee_contain;
    }

    public void setFee_contain(String fee_contain) {
        this.fee_contain = fee_contain;
    }

    public String getFee_not_contain() {
        return fee_not_contain;
    }

    public void setFee_not_contain(String fee_not_contain) {
        this.fee_not_contain = fee_not_contain;
    }

    public String getPre_notice() {
        return pre_notice;
    }

    public void setPre_notice(String pre_notice) {
        this.pre_notice = pre_notice;
    }

    public List<RoutePlanNodeDto> getPlan_node_info() {
        return plan_node_info;
    }

    public void setPlan_node_info(List<RoutePlanNodeDto> plan_node_info) {
        this.plan_node_info = plan_node_info;
    }

    public List<RoutePlanBasePriceDto> getPlan_price_info() {
        return plan_price_info;
    }

    public void setPlan_price_info(List<RoutePlanBasePriceDto> plan_price_info) {
        this.plan_price_info = plan_price_info;
    }

    public String getRoute_plan_code() {
        return route_plan_code;
    }

    public void setRoute_plan_code(String route_plan_code) {
        this.route_plan_code = route_plan_code;
    }

    public String getRoute_plan_name() {
        return route_plan_name;
    }

    public void setRoute_plan_name(String route_plan_name) {
        this.route_plan_name = route_plan_name;
    }

    public String getDeparture_area_str() {
        return departure_area_str;
    }

    public void setDeparture_area_str(String departure_area_str) {
        this.departure_area_str = departure_area_str;
    }

    public String getDestination_area_str() {
        return destination_area_str;
    }

    public void setDestination_area_str(String destination_area_str) {
        this.destination_area_str = destination_area_str;
    }

    public String getRendezvous_area_str() {
        return rendezvous_area_str;
    }

    public void setRendezvous_area_str(String rendezvous_area_str) {
        this.rendezvous_area_str = rendezvous_area_str;
    }
}
