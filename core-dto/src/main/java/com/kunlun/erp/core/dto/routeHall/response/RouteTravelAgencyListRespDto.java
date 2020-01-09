package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteTravelAgencyListRespDto
 * @Description 地接供应商列表响应
 * @Author Jm.zhang
 * @Date 2019/12/25 12:19
 * @Version 1.0
 **/
@ApiModel(description = "地接供应商列表响应")
public class RouteTravelAgencyListRespDto {
    /**
     * 地接供应商数据
     */
    private List<RouteTravelAgencyDto> travel_agency_data;

    /**
     * 大厅线路产品详情
     */
    private HallProductDetailRespDto hall_data;

    public List<RouteTravelAgencyDto> getTravel_agency_data() {
        return travel_agency_data;
    }

    public void setTravel_agency_data(List<RouteTravelAgencyDto> travel_agency_data) {
        this.travel_agency_data = travel_agency_data;
    }

    public HallProductDetailRespDto getHall_data() {
        return hall_data;
    }

    public void setHall_data(HallProductDetailRespDto hall_data) {
        this.hall_data = hall_data;
    }


}
