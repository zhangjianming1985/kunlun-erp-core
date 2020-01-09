package com.kunlun.erp.core.dto.routeOrder;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOrderBaseInfoDto
 * @Description 线路订单基础数据
 * @Author Jm.zhang
 * @Date 2019/12/23 9:49
 * @Version 1.0
 **/
@ApiModel(description = "线路订单数据")
public class RouteOrderBaseInfoDto {
    @ApiModelProperty(value = "订单编号",example = "123")
    private String order_code;

    @ApiModelProperty(value = "价格套餐",example ="默认套餐1" )
    private String price_plan_name;

    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    private String group_code;

    @ApiModelProperty(value = "发团日期",example ="2019-12-12" )
    private String departure_date;

    @ApiModelProperty(value = "散团日期",example ="2019-12-20" )
    private String disband_date;

    @ApiModelProperty(required = true,value = "销售渠道编号",example = "1000001610779011")
    private String company_code;

    @ApiModelProperty(value = "销售渠道名称",example = "测试销售渠道")
    private String company_name;

    @ApiModelProperty(required = true,value = "联系人编号",example = "3000001915563924")
    private String contact_code;

    @ApiModelProperty(value = "联系人名字",example = "小安")
    private String contact_name;

    @ApiModelProperty(value = "联系人手机号码",example = "15915328866")
    private String contact_mobile;


    @ApiModelProperty(value = "联系人座机号码",example = "0755-28699988")
    private String contact_phone;

    @ApiModelProperty(value = "渠道内部单号",example = "55555555555")
    private String company_order_code;

    @ApiModelProperty(value = "客源地")
    private AreaDto client_area;

    @ApiModelProperty(value = "客源地释义",example = " 国内/华南/广东/深圳/龙岗")
    private String client_area_str;


    @ApiModelProperty(value = "用房标准：0=准三星、1=挂三星、2=准四星、3=挂四星、4=准五星、5=挂五星、6其他",example = "0")
    private Integer hotel_level;

    @ApiModelProperty(required = true,value = "用房标准释义",example = "准三星")
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

    @ApiModelProperty(required = true,value = "成人数量",example = "1")
    private Integer adult_count;

    @ApiModelProperty(required = true,value = "儿童数量",example = "1")
    private Integer children_count;

    @ApiModelProperty(required = true,value = "状态 0=确认、1=站位、2=取消",example = "1")
    private Integer state;

    @ApiModelProperty(value = "状态释义",example = "站位")
    private String state_str;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    @ApiModelProperty(value = "审核状态,0=未审核、1=通过、2=驳回",example = "2")
    private Integer audit_state;

    @ApiModelProperty(value = "审核状态释义",example = "通过")
    private String audit_state_str;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContact_code() {
        return contact_code;
    }

    public void setContact_code(String contact_code) {
        this.contact_code = contact_code;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public String getCompany_order_code() {
        return company_order_code;
    }

    public void setCompany_order_code(String company_order_code) {
        this.company_order_code = company_order_code;
    }


    public String getClient_area_str() {
        return client_area_str;
    }

    public void setClient_area_str(String client_area_str) {
        this.client_area_str = client_area_str;
    }

    public Integer getHotel_level() {
        return hotel_level;
    }


    public void setHotel_level(Integer hotel_level) {
        this.hotel_level = hotel_level;
        if (hotel_level!= null){
            this.setHotel_level_str(SysConstant.RoomLevel.getRoomLevel(hotel_level).getName());
        }

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
        if (state!=null){
            this.setState_str(SysConstant.RouteOrderState.getRouteOrderState(state).getName());
        }
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getState_str() {
        return state_str;
    }

    public void setState_str(String state_str) {
        this.state_str = state_str;
    }

    public AreaDto getClient_area() {
        return client_area;
    }

    public void setClient_area(AreaDto client_area) {
        this.client_area = client_area;
    }

    public String getHotel_level_str() {
        return hotel_level_str;
    }

    public void setHotel_level_str(String hotel_level_str) {
        this.hotel_level_str = hotel_level_str;
    }

    public String getPrice_plan_name() {
        return price_plan_name;
    }

    public void setPrice_plan_name(String price_plan_name) {
        this.price_plan_name = price_plan_name;
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

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
    public Integer getAudit_state() {
        return audit_state;
    }

    public void setAudit_state(Integer audit_state) {
        this.audit_state = audit_state;
        if (audit_state!=null){
            this.setAudit_state_str(SysConstant.FinanceAuditStatus.getFinanceAuditStatus(audit_state).getName());
        }
    }

    public String getAudit_state_str() {
        return audit_state_str;
    }

    public void setAudit_state_str(String audit_state_str) {
        this.audit_state_str = audit_state_str;
    }
}
