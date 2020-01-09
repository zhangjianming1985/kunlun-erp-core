package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteStatisticsRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteStatisticsRespDto;

/**
 * @InterfaceName RouteStatisticsService
 * @Description 线路团收支汇总服务接口
 * @Author Jm.zhang
 * @Date 2019/12/26 9:54
 * @Version 1.0
 **/
public interface RouteStatisticsService {
    AbstractResponse<RouteStatisticsRespDto> routeStatistic (RouteStatisticsRequest request);
}