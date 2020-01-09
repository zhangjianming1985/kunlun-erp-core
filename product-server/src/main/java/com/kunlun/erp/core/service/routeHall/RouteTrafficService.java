package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTrafficAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTrafficListRespDto;

/**
 * @InterfaceName RouteTrafficService
 * @Description 线路团交通票务服务接口
 * @Author Jm.zhang
 * @Date 2019-12-24 23:52
 * @Version 1.0
 **/
public interface RouteTrafficService {
    AbstractResponse<RouteTrafficListRespDto> list (RouteTrafficListRequest request);

    AbstractResponse<RouteTrafficAddRespDto> add (RouteTrafficAddRequest request);
}
