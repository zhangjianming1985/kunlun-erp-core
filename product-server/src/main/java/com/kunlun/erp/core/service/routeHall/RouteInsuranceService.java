package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteInsuranceAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteInsuranceListRespDto;

/**
 * @InterfaceName RouteInsuranceService
 * @Description 线路团保险服务接口
 * @Author Jm.zhang
 * @Date 2019-12-23 0:18
 * @Version 1.0
 **/
public interface RouteInsuranceService {
    AbstractResponse<RouteInsuranceListRespDto> list (RouteInsuranceListRequest request);

    AbstractResponse<RouteInsuranceAddRespDto> add (RouteInsuranceAddRequest request);
}
