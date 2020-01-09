package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName RouteProductCopyRespDto
 * @Description 产品复制结果响应
 * @Author Jm.zhang
 * @Date 2019-12-31 1:28
 * @Version 1.0
 **/

@ApiModel(description = "产品复制结果响应")
public class RouteProductCopyRespDto {
    @ApiModelProperty(value = "新的产品编号集合")
    private List<String> new_product_code;

    @ApiModelProperty(value = "新的线路编号集合")
    private List<String> new_route_code;

    @ApiModelProperty(value = "新的行程方案编号集合")
    private List<String> new_route_plan_code;

    @ApiModelProperty(value = "新的节点编号集合")
    private List<String> new_node_code;

    @ApiModelProperty(value = "新的基础价格编号集合")
    private List<String> new_base_price_code;

    public List<String> getNew_product_code() {
        return new_product_code;
    }

    public void setNew_product_code(List<String> new_product_code) {
        this.new_product_code = new_product_code;
    }

    public List<String> getNew_route_code() {
        return new_route_code;
    }

    public void setNew_route_code(List<String> new_route_code) {
        this.new_route_code = new_route_code;
    }

    public List<String> getNew_route_plan_code() {
        return new_route_plan_code;
    }

    public void setNew_route_plan_code(List<String> new_route_plan_code) {
        this.new_route_plan_code = new_route_plan_code;
    }

    public List<String> getNew_node_code() {
        return new_node_code;
    }

    public void setNew_node_code(List<String> new_node_code) {
        this.new_node_code = new_node_code;
    }

    public List<String> getNew_base_price_code() {
        return new_base_price_code;
    }

    public void setNew_base_price_code(List<String> new_base_price_code) {
        this.new_base_price_code = new_base_price_code;
    }
}
