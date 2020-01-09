package com.kunlun.erp.core.dto.finance.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName CollectedListReq
 * @Description 应收款检索请求
 * @Author Jm.zhang
 * @Date 2019/12/27 13:29
 * @Version 1.0
 **/
@ApiModel(description = "应收款检索请求")
public class CollectedListReqDto {
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(value = "销售渠道名称",example = "天王渠道")
    private String company_name;

    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    private String group_code;

    @ApiModelProperty(required = true,value = "收款状态,0=待收款、1=已结清",example = "0")
    private Integer collected_state;

    @ApiModelProperty(required = true,value = "财务结算模式：0=现结（实时）结算、1=周结、2=月结、3=季结、4=年结",example = "2")
    private Integer settlement_mode;

    @ApiModelProperty(value = "发团日期开始",example ="2019-12-12" )
    private String departure_date_start;

    @ApiModelProperty(value = "发团日期结束",example ="2019-12-15" )
    private String departure_date_end;

    @ApiModelProperty(value = "订单编号集合")
    private List<String> order_list;




    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Integer getCollected_state() {
        return collected_state;
    }

    public void setCollected_state(Integer collected_state) {
        this.collected_state = collected_state;
    }

    public Integer getSettlement_mode() {
        return settlement_mode;
    }

    public void setSettlement_mode(Integer settlement_mode) {
        this.settlement_mode = settlement_mode;
    }

    public String getDeparture_date_start() {
        return departure_date_start;
    }

    public void setDeparture_date_start(String departure_date_start) {
        this.departure_date_start = departure_date_start;
    }

    public String getDeparture_date_end() {
        return departure_date_end;
    }

    public void setDeparture_date_end(String departure_date_end) {
        this.departure_date_end = departure_date_end;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public List<String> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<String> order_list) {
        this.order_list = order_list;
    }
}
