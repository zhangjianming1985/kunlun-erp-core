package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTicketAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTicketListRespDto;

/**
 * @InterfaceName RouteTicketService
 * @Description 线路团景点门票服务接口
 * @Author Jm.zhang
 * @Date 2019/12/20 18:23
 * @Version 1.0
 **/
public interface RouteTicketService {

    AbstractResponse<RouteTicketListRespDto> list (RouteTicketListRequest request);

    AbstractResponse<RouteTicketAddRespDto> add (RouteTicketAddRequest request);
}