package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteInsuranceDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteInsuranceListRespDto
 * @Description 线路保险数据响应
 * @Author Jm.zhang
 * @Date 2019-12-23 0:04
 * @Version 1.0
 **/
@ApiModel(description = "线路保险数据响应")
public class RouteInsuranceListRespDto {
    private List<RouteInsuranceDto> insurance_data;

    public List<RouteInsuranceDto> getInsurance_data() {
        return insurance_data;
    }

    public void setInsurance_data(List<RouteInsuranceDto> insurance_data) {
        this.insurance_data = insurance_data;
    }
}
