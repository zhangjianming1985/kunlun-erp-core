package com.kunlun.erp.core.dto.product.response;

import com.kunlun.erp.core.dto.product.RoutePlanShortDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RoutePlanShortRespDto
 * @Description 线路产品行程方案响应
 * @Author Jm.zhang
 * @Date 2019/12/16 17:40
 * @Version 1.0
 **/
@ApiModel(description = "线路产品行程方案响应")
public class RoutePlanShortRespDto {

    private List<RoutePlanShortDto> plan_data;

    public List<RoutePlanShortDto> getPlan_data() {
        return plan_data;
    }

    public void setPlan_data(List<RoutePlanShortDto> plan_data) {
        this.plan_data = plan_data;
    }
}
