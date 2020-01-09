package com.kunlun.erp.core.service.order;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDeleteRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDetailRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListRequest;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderAddRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderDeleteRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderDetailRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderListRespDto;

/**
 * @InterfaceName RouteOrderService
 * @Description 线路订单服务接口
 * @Author Jm.zhang
 * @Date 2019/12/23 12:10
 * @Version 1.0
 **/
public interface RouteOrderService {
    Integer deleteByCompanyCode(String company_code);

    AbstractResponse<RouteOrderDetailRespDto> detail(RouteOrderDetailRequest request);

    AbstractResponse<RouteOrderAddRespDto> add(RouteOrderAddRequest request);

    AbstractResponse<RouteOrderListRespDto> list(RouteOrderListRequest request);

    AbstractResponse<RouteOrderDeleteRespDto> delete(RouteOrderDeleteRequest request);


}