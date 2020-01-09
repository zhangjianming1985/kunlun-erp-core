package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName RouteProductDto
 * @Description 线路产品数据
 * @Author Jm.zhang
 * @Date 2019/12/7 14:39
 * @Version 1.0
 **/
@ApiModel(description = "线路产品数据")
public class RouteProductDto {

    @ApiModelProperty(value = "产品唯一编号， 创建时 无需传参")
    private String product_code;

    @ApiModelProperty(value = "产品分类编号")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_CATEGORY_CODE_INVALID)
    private String product_category_code;

    @ApiModelProperty(required = true,value = "线路名称，最多50个字，必填",example = "东南亚豪华游")
    @NotBlank(message = ErrorCodeConstant.PRODUCT_NAME_INVALID)
    private String product_name;

    @ApiModelProperty(value = "产品描述,如是线路产品则为线路特色",example = "东南亚豪华游东南亚豪华游东南亚豪华游")
    private String product_description;
    /**
     * 产品区域信息
     */
    @NotNull(message = ErrorCodeConstant.AREA_DATA_NULL)
    @Valid
    private AreaDto area_info;

    @ApiModelProperty(value = "产品区域释义",example = "国内/华南/广东/深圳/龙岗")
    private String area_str;

    /**
     * 线路产品基本信息
     */
    @NotNull(message = ErrorCodeConstant.PRODUCT_ROUTE_BASE_INFO_INVALID)
    @Valid
    private RouteBaseDto route_base_info;

    /**
     * 线路计划数据 行程1  行程2 ...
     */
    @NotEmpty(message = ErrorCodeConstant.PRODUCT_ROUTE_PLAN_INFO_INVALID)
    @Valid
    private List<RoutePlanDto> route_plan_info;


    public String getProduct_category_code() {
        return product_category_code;
    }

    public void setProduct_category_code(String product_category_code) {
        this.product_category_code = product_category_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public AreaDto getArea_info() {
        return area_info;
    }

    public void setArea_info(AreaDto area_info) {
        this.area_info = area_info;
    }

    public RouteBaseDto getRoute_base_info() {
        return route_base_info;
    }

    public void setRoute_base_info(RouteBaseDto route_base_info) {
        this.route_base_info = route_base_info;
    }

    public List<RoutePlanDto> getRoute_plan_info() {
        return route_plan_info;
    }

    public void setRoute_plan_info(List<RoutePlanDto> route_plan_info) {
        this.route_plan_info = route_plan_info;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getArea_str() {
        return area_str;
    }

    public void setArea_str(String area_str) {
        this.area_str = area_str;
    }
}

