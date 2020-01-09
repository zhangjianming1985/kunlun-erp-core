package com.kunlun.erp.core.dto.product.response;

import com.kunlun.erp.core.dto.product.RoutePlanShortDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName RouteProductAddRespDto
 * @Description 创建线路产品结果响应
 * @Author Jm.zhang
 * @Date 2019/12/9 11:37
 * @Version 1.0
 **/
@ApiModel(description = "创建线路产品结果响应")
public class RouteProductAddRespDto {
    @ApiModelProperty(value = "产品唯一编号",example = "6500008545688")
    private String product_code;

    @ApiModelProperty(value = "线路行程计划数据")
    private List<RoutePlanShortDto> route_plan_list;


    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public List<RoutePlanShortDto> getRoute_plan_list() {
        return route_plan_list;
    }

    public void setRoute_plan_list(List<RoutePlanShortDto> route_plan_list) {
        this.route_plan_list = route_plan_list;
    }
}
