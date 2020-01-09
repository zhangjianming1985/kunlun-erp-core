package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.RoutePlanNodeDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RoutePlanNode;

import java.util.List;

/**
 * @InterfaceName RoutePlanNodeService
 * @Description 线路计划节点服务接口
 * @Author Jm.zhang
 * @Date 2019/12/10 18:00
 * @Version 1.0
 **/
public interface RoutePlanNodeService {
    RoutePlanNode add(String plan_code, AbstractResponse<UserInfoRespDto> user_info, RoutePlanNodeDto node_dto);

    RoutePlanNode update(RoutePlanNodeDto node_dto);

    void delete(String plan_code,List<String> node_list);
}