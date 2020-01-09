package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName RouteMealDto
 * @Description 线路团关联用餐数据
 * @Author Jm.zhang
 * @Date 2019-12-22 18:06
 * @Version 1.0
 **/
@ApiModel(description = "线路团关联用餐数据")
public class RouteMealDto {
    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String meal_code;

    @ApiModelProperty(required = true,value = "供应商编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(value = "供应商名称,新增时 无需传参",example = "兴旺餐饮公司")
    private String company_name;

    @ApiModelProperty(required = true,value = "联系人编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String person_code;

    @ApiModelProperty(value = "联系人名字,新增时 无需传参",example = "小王")
    private String person_name;

    @ApiModelProperty(value = "联系人手机号码,新增时 无需传参",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(value = "用餐类型：0=早餐、1=中餐、2=晚餐",example = "0")
    @NotNull(message = ErrorCodeConstant.MEAL_TYPE_INVALID)
    private Integer meal_type;

    @ApiModelProperty(required = true,value = "用餐日期 格式 yyyy-MM-dd",example = "2019-12-31")
    @NotBlank(message = ErrorCodeConstant.MEAL_DATE_INVALID)
    private String meal_date;

    @ApiModelProperty(required = true,value = "货币：USD、CNY",example = "CNY")
    @NotBlank(message = ErrorCodeConstant.MEAL_CURRENCY_INVALID)
    private String currency;

    @ApiModelProperty(required = true,value = "单价",example = "10.50")
    @NotBlank(message = ErrorCodeConstant.MEAL_PRICE_INVALID)
    private String fee;

    @ApiModelProperty(required = true,value = "用餐人数",example = "5")
    @NotNull(message = ErrorCodeConstant.MEAL_DINNERS_COUNT_INVALID)
    private Integer diners_count;

    @ApiModelProperty(required = true,value = "免费人数",example = "1")
    @NotNull(message = ErrorCodeConstant.MEAL_FREE_COUNT_INVALID)
    private Integer free_count;

    @ApiModelProperty(required = true,value = "总价",example = "105.00")
    @NotBlank(message = ErrorCodeConstant.MEAL_TOTAL_PRICE_INVALID)
    private String fee_total;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    public String getMeal_code() {
        return meal_code;
    }

    public void setMeal_code(String meal_code) {
        this.meal_code = meal_code;
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

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }

    public Integer getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(Integer meal_type) {
        this.meal_type = meal_type;
    }

    public String getMeal_date() {
        return meal_date;
    }

    public void setMeal_date(String meal_date) {
        this.meal_date = meal_date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getDiners_count() {
        return diners_count;
    }

    public void setDiners_count(Integer diners_count) {
        this.diners_count = diners_count;
    }

    public Integer getFree_count() {
        return free_count;
    }

    public void setFree_count(Integer free_count) {
        this.free_count = free_count;
    }

    public String getFee_total() {
        return fee_total;
    }
    public Double getFee_total_double() {
        return new BigDecimal(fee_total).doubleValue();
    }


    public void setFee_total(String fee_total) {
        this.fee_total = fee_total;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
