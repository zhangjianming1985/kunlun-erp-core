package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTravelAgencyAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTravelAgencyListRespDto;

/**
 * @ClassName RouteTravelAgencyService
 * @Description 线路团地接旅行社服务接口
 * @Author Jm.zhang
 * @Date 2019/12/25 13:28
 * @Version 1.0
 **/
public interface RouteTravelAgencyService {
    AbstractResponse<RouteTravelAgencyListRespDto> detail (RouteTravelAgencyListRequest request);

    AbstractResponse<RouteTravelAgencyAddRespDto> add (RouteTravelAgencyAddRequest request);
}
