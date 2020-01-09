package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteResideAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteResideListRespDto;

/**
 * @InterfaceName RouteResideService
 * @Description 线路团住宿服务接口
 * @Author Jm.zhang
 * @Date 2019/12/20 16:46
 * @Version 1.0
 **/
public interface RouteResideService {

    AbstractResponse<RouteResideListRespDto> list (RouteResideListRequest request);

    AbstractResponse<RouteResideAddRespDto> add (RouteResideAddRequest request);
}