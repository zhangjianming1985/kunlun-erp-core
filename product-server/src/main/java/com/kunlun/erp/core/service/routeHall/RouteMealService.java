package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteMealAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteMealListRespDto;

/**
 * @InterfaceName RouteMealService
 * @Description 线路团用餐服务接口
 * @Author Jm.zhang
 * @Date 2019-12-22 19:01
 * @Version 1.0
 **/
public interface RouteMealService {

    AbstractResponse<RouteMealListRespDto> list (RouteMealListRequest request);

    AbstractResponse<RouteMealAddRespDto> add (RouteMealAddRequest request);
}
