package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteOtherAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteOtherListRespDto;

/**
 * @InterfaceName RouteOtherService
 * @Description 线路团其他服务接口
 * @Author Jm.zhang
 * @Date 2019-12-23 1:07
 * @Version 1.0
 **/
public interface RouteOtherService {
    AbstractResponse<RouteOtherListRespDto> list (RouteOtherListRequest request);

    AbstractResponse<RouteOtherAddRespDto> add (RouteOtherAddRequest request);
}
