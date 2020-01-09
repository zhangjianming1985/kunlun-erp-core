package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteMotorcadeAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteMotorcadeListRespDto;

/**
 * @InterfaceName RouteMotorcadeService
 * @Description 线路团车队服务接口
 * @Author Jm.zhang
 * @Date 2019-12-22 22:28
 * @Version 1.0
 **/
public interface RouteMotorcadeService {
    AbstractResponse<RouteMotorcadeListRespDto> list (RouteMotorcadeListRequest request);

    AbstractResponse<RouteMotorcadeAddRespDto> add (RouteMotorcadeAddRequest request);
}
