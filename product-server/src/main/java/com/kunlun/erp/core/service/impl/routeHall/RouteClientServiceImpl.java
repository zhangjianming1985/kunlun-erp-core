package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteClientDto;
import com.kunlun.erp.core.dto.routeHall.SalesChannelDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteClientListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteClientListRespDto;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.entity.RouteOrder;
import com.kunlun.erp.core.mapper.OrderClientMapper;
import com.kunlun.erp.core.mapper.RouteOrderMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RouteClientServiceImpl
 * @Description 线路团的出游人服务实现
 * @Author Jm.zhang
 * @Date 2019/12/24 19:47
 * @Version 1.0
 **/
@Service(value = "route_client_service")
public class RouteClientServiceImpl extends BaseService implements RouteClientService {
    @Resource
    private RouteOrderMapper order_dao;
    @Resource
    private OrderClientMapper client_dao;

    @Override
    public AbstractResponse<RouteClientListRespDto> list(RouteClientListRequest request) {
        AbstractResponse<RouteClientListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteClientListRespDto resp_body = new RouteClientListRespDto();
        List<RouteClientDto> route_client = new ArrayList<>();
        //所有订单
        List<RouteOrder> order_list = order_dao.selectByGroupCode(request.getBody().getGroup_code());
        if (order_list!=null && !order_list.isEmpty()){

            //循环订单
           for (RouteOrder order_record : order_list){
               RouteClientDto rcd = new RouteClientDto();
               //销售渠道
               SalesChannelDto sales_data = order_dao.selectSalesChannelByOrderCode(order_record.getOrder_code());
               List<OrderClientDto> client_data = client_dao.selectDtoByOrderCode(order_record.getOrder_code());
               rcd.setSales_channel_data(sales_data);
               rcd.setClient_data(client_data);
               route_client.add(rcd);
           }

        }
        resp_body.setRoute_client(route_client);
        response.setBody(resp_body);
        return response;
    }
}
