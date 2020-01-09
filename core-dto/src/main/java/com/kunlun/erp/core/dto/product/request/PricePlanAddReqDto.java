package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.product.RoutePriceCostDetailDto;
import com.kunlun.erp.core.dto.product.RoutePricePlanDetailDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName PricePlanAddReqDto
 * @Description 创建价格方案
 * @Author Jm.zhang
 * @Date 2019/12/16 18:28
 * @Version 1.0
 **/
@ApiModel(description = "创建价格方案")
public class PricePlanAddReqDto {
    @ApiModelProperty(value = "价格方案编号,创建时无需传参，更新方案名称时 需要传参",example = "125855")
    private String  price_plan_code;

    @ApiModelProperty(required = true,value = "价格方案名称",example = "价格方案一")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String price_plan_name;

    @ApiModelProperty(required = true,value = "行程方案编号",example = "8000001339037607")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NOT_EXIST)
    private String plan_code;

    @ApiModelProperty(required = true,value = "日期，格式 yyyy-MM-dd",example = "2019-12-12")
    private List<String> date_info;

    @NotNull(message = ErrorCodeConstant.PRICE_PLAN_DETAIL_INVALID)
    private RoutePricePlanDetailDto price_info;

    //成本明细
    private List<RoutePriceCostDetailDto> cost_info;

    public String getPrice_plan_name() {
        return price_plan_name;
    }

    public void setPrice_plan_name(String price_plan_name) {
        this.price_plan_name = price_plan_name;
    }

    public String getPlan_code() {
        return plan_code;
    }

    public void setPlan_code(String plan_code) {
        this.plan_code = plan_code;
    }

    public List<String> getDate_info() {
        return date_info;
    }

    public void setDate_info(List<String> date_info) {
        this.date_info = date_info;
    }

    public RoutePricePlanDetailDto getPrice_info() {
        return price_info;
    }

    public void setPrice_info(RoutePricePlanDetailDto price_info) {
        this.price_info = price_info;
    }

    public List<RoutePriceCostDetailDto> getCost_info() {
        return cost_info;
    }

    public void setCost_info(List<RoutePriceCostDetailDto> cost_info) {
        this.cost_info = cost_info;
    }

    public String getPrice_plan_code() {
        return price_plan_code;
    }

    public void setPrice_plan_code(String price_plan_code) {
        this.price_plan_code = price_plan_code;
    }
}
