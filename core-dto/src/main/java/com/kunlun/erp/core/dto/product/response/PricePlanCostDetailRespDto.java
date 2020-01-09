package com.kunlun.erp.core.dto.product.response;

import com.kunlun.erp.core.dto.product.RoutePriceCostDetailDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName PricePlanCostDetailRespDto
 * @Description 线路产品成本明细响应
 * @Author Jm.zhang
 * @Date 2019/12/18 8:45
 * @Version 1.0
 **/
@ApiModel(description = "线路产品成本明细响应")
public class PricePlanCostDetailRespDto {

    private List<RoutePriceCostDetailDto> cost_detail;

    public List<RoutePriceCostDetailDto> getCost_detail() {
        return cost_detail;
    }

    public void setCost_detail(List<RoutePriceCostDetailDto> cost_detail) {
        this.cost_detail = cost_detail;
    }
}
