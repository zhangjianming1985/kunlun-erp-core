package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteGuideAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteGuideListRespDto;

/**
 * @InterfaceName RouteGuideService
 * @Description 线路导服服务接口
 * @Author Jm.zhang
 * @Date 2019/12/19 19:10
 * @Version 1.0
 **/
public interface RouteGuideService {
    AbstractResponse<RouteGuideListRespDto> list (RouteGuideListRequest request);

    AbstractResponse<RouteGuideAddRespDto> add (RouteGuideAddRequest request);
}