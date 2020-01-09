package com.kunlun.erp.core.dto.finance;

import com.alibaba.fastjson.annotation.JSONField;
import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CollectedDto
 * @Description 销售渠道应收款数据详情
 * @Author Jm.zhang
 * @Date 2019/12/27 13:36
 * @Version 1.0
 **/
@ApiModel(description = "销售渠道应收款数据详情")
public class CollectedDto {

    @ApiModelProperty(value = "订单号",example = "886688")
    private String order_code;

    @ApiModelProperty(value = "销售渠道编号",example = "888554888")
    private String company_code;

    @ApiModelProperty(value = "销售渠道名称",example = "天王渠道")
    private String company_name;

    @JSONField(serialize = false)
    @ApiModelProperty(required = true,value = "财务结算模式：0=现结（实时）结算、1=周结、2=月结、3=季结、4=年结",example = "2")
    private Integer settlement_mode;

    @ApiModelProperty(required = true,value = "财务结算模式释义",example = "月结")
    private String settlement_mode_str;

    @ApiModelProperty(value = "团号",example = "886688")
    private String group_code;

    @ApiModelProperty(value = "应收金额",example = "100.00")
    private String collect_amount;

    @ApiModelProperty(value = "已收金额",example = "40.00")
    private String collected_amount;

    @ApiModelProperty(value = "未收金额",example = "60.00")
    private String remaining_amount;

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

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getCollect_amount() {
        return collect_amount;
    }

    public void setCollect_amount(String collect_amount) {
        this.collect_amount = collect_amount;
    }

    public String getCollected_amount() {
        return collected_amount;
    }

    public void setCollected_amount(String collected_amount) {
        this.collected_amount = collected_amount;
    }

    public String getRemaining_amount() {
        return remaining_amount;
    }

    public void setRemaining_amount(String remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public Integer getSettlement_mode() {
        return settlement_mode;
    }

    public void setSettlement_mode(Integer settlement_mode) {
        this.settlement_mode = settlement_mode;
        if (settlement_mode!=null){
            this.setSettlement_mode_str(SysConstant.FinanceSettlementMode.getFinanceSettlementMode(settlement_mode).getName());
        }
    }

    public String getSettlement_mode_str() {
        return settlement_mode_str;
    }

    public void setSettlement_mode_str(String settlement_mode_str) {
        this.settlement_mode_str = settlement_mode_str;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
}
