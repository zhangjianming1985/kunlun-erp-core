package com.kunlun.erp.core.service.order;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteOrder;

import java.util.List;

/**
 * @InterfaceName OrderIncomeService
 * @Description 订单的团款服务接口
 * @Author Jm.zhang
 * @Date 2019/12/26 11:38
 * @Version 1.0
 **/
public interface OrderIncomeService {
    void add(RouteOrder order_record, AbstractResponse<UserInfoRespDto> user_info, List<OrderIncomeDto> income_data);
}