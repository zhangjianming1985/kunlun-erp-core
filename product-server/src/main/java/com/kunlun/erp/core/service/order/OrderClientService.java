package com.kunlun.erp.core.service.order;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.dto.routeOrder.request.ClientAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.ClientListRequest;
import com.kunlun.erp.core.dto.routeOrder.response.ClientAddRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.ClientListRespDto;

import java.util.List;

/**
 * @InterfaceName OrderClientService
 * @Description 订单的客户服务接口
 * @Author Jm.zhang
 * @Date 2019/12/24 11:49
 * @Version 1.0
 **/
public interface OrderClientService {

    AbstractResponse<ClientAddRespDto> add(ClientAddRequest request);

    AbstractResponse<ClientListRespDto> list(ClientListRequest request);

    void updateTrafficState(List<OrderClientDto> client_data);

    int deleteByOrderCode(String order_code);

    int deleteByCompanyCode(String company_code);

}