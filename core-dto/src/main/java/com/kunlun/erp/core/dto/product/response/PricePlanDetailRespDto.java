package com.kunlun.erp.core.dto.product.response;

import com.kunlun.erp.core.dto.product.PricePlanDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName PricePlanDetailRespDto
 * @Description 价格套餐详情
 * @Author Jm.zhang
 * @Date 2019-12-17 0:59
 * @Version 1.0
 **/
@ApiModel(description = "价格套餐详情")
public class PricePlanDetailRespDto {
    private List<PricePlanDto> price_plan_data;

    public List<PricePlanDto> getPrice_plan_data() {
        return price_plan_data;
    }

    public void setPrice_plan_data(List<PricePlanDto> price_plan_data) {
        this.price_plan_data = price_plan_data;
    }
}
