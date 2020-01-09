package com.kunlun.erp.core.dto.routeOrder;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @ClassName OrderListDto
 * @Description 线路订单列表数据
 * @Author Jm.zhang
 * @Date 2019-12-23 23:19
 * @Version 1.0
 **/
@ApiModel(description = "线路订单列表数据")
public class OrderListDto {
    @ApiModelProperty(value = "订单号",example = "16000001744754581")
    private String order_code;

    @ApiModelProperty(value = "产品名称",example = "东南亚豪华游1111222")
    private String product_name;

    @ApiModelProperty(value = "发团日期",example ="2019-12-12" )
    private String departure_date;

    @ApiModelProperty(value = "成人数量",example = "1")
    private Integer adult_count;

    @ApiModelProperty(value = "儿童数量",example = "1")
    private Integer children_count;

    @ApiModelProperty(value = "金额",example = "10.50")
    private String amount;

    @ApiModelProperty(value = "报名时间",example ="2019-12-10" )
    private String create_time;

    @ApiModelProperty(value = "状态 0=确认、1=站位、2=取消",example = "1")
    private Integer state;



    @ApiModelProperty(value = "状态释义",example = "站位")
    private String state_str;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        if (StringUtils.isNotBlank(create_time)){
            Date d =DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,create_time);
            this.create_time = DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,d);
        }else{
            this.create_time = create_time;
        }

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
    public String getState_str() {
        return state_str;
    }

    public void setState_str(String state_str) {
        this.state_str = state_str;
    }


}
