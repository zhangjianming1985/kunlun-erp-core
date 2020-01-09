package com.kunlun.erp.core.dto.routeOrder.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName RouteOrderAddReqDto
 * @Description 创建编辑线路订单
 * @Author Jm.zhang
 * @Date 2019/12/23 15:58
 * @Version 1.0
 **/

@ApiModel(description = "创建编辑线路订单")
public class RouteOrderAddReqDto {
    @ApiModelProperty(required = true,value = "订单号，创建时无需传参",example = "1522255")
    private String order_code;

    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    @ApiModelProperty(required = true,value = "销售渠道编号",example = "1000001610779011")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(required = true,value = "联系人编号",example = "3000001915563924")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String contact_code;

    @ApiModelProperty(value = "渠道内部单号",example = "55555555555")
    private String company_order_code;

    @ApiModelProperty(value = "客源地")
    @NotNull(message = ErrorCodeConstant.AREA_DATA_NULL)
    private AreaDto client_area;

    @ApiModelProperty(value = "用房标准：0=准三星、1=挂三星、2=准四星、3=挂四星、4=准五星、5=挂五星、6其他",example = "0")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_ROOM_LEVEL_INVALID)
    private Integer hotel_level;

    @ApiModelProperty(required = true,value = "标准房数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_STANDARD_ROOM_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_STANDARD_ROOM_COUNT_INVALID)
    private Integer room_standard_count;

    @ApiModelProperty(required = true,value = "大床房数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_BIG_ROOM_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_BIG_ROOM_COUNT_INVALID)
    private Integer room_big_count;

    @ApiModelProperty(required = true,value = "三人间数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_THREE_ROOM_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_THREE_ROOM_COUNT_INVALID)
    private Integer room_three_count;

    @ApiModelProperty(required = true,value = "陪房数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_ACCOMPANY_ROOM_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_ACCOMPANY_ROOM_COUNT_INVALID)
    private Integer accompany_room_count;

    @ApiModelProperty(required = true,value = "不用房数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_NO_ROOM_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_NO_ROOM_COUNT_INVALID)
    private Integer no_room_count;

    @ApiModelProperty(required = true,value = "成人数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_ADULT_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_ADULT_COUNT_INVALID)
    private Integer adult_count;

    @ApiModelProperty(required = true,value = "儿童数量",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_CHILDREN_COUNT_INVALID)
    @Min(value = 0,message = ErrorCodeConstant.ROUTE_ORDER_CHILDREN_COUNT_INVALID)
    private Integer children_count;

    @ApiModelProperty(required = true,value = "状态 0=确认、1=站位、2=取消",example = "1")
    @NotNull(message = ErrorCodeConstant.ROUTE_ORDER_STATE_INVALID)
    private Integer state;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    /**
     * 进账明细
     */
    private List<OrderIncomeDto> income_data;


    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getContact_code() {
        return contact_code;
    }

    public void setContact_code(String contact_code) {
        this.contact_code = contact_code;
    }

    public String getCompany_order_code() {
        return company_order_code;
    }

    public void setCompany_order_code(String company_order_code) {
        this.company_order_code = company_order_code;
    }

    public AreaDto getClient_area() {
        return client_area;
    }

    public void setClient_area(AreaDto client_area) {
        this.client_area = client_area;
    }

    public Integer getHotel_level() {
        return hotel_level;
    }

    public void setHotel_level(Integer hotel_level) {
        this.hotel_level = hotel_level;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public List<OrderIncomeDto> getIncome_data() {
        return income_data;
    }

    public void setIncome_data(List<OrderIncomeDto> income_data) {
        this.income_data = income_data;
    }
}
