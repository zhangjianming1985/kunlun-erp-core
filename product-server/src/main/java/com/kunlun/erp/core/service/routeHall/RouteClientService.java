package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteClientListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteClientListRespDto;

/**
 * @InterfaceName RouteClientService
 * @Description 线路团出游人服务接口
 * @Author Jm.zhang
 * @Date 2019/12/24 19:47
 * @Version 1.0
 **/
public interface RouteClientService {
    AbstractResponse<RouteClientListRespDto> list(RouteClientListRequest request);
}