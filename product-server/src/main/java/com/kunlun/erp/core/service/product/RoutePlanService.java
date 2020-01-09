package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.RoutePlanDto;
import com.kunlun.erp.core.dto.product.request.RoutePlanShortRequest;
import com.kunlun.erp.core.dto.product.response.RoutePlanShortRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RoutePlan;

import java.util.List;

/**
 * @InterfaceName RoutePlanService
 * @Description 线路产品的行程计划服务接口
 * @Author Jm.zhang
 * @Date 2019/12/10 17:33
 * @Version 1.0
 **/
public interface RoutePlanService {
    RoutePlan add(String product_code, String route_code, AbstractResponse<UserInfoRespDto> user_info, RoutePlanDto plan_dto);

    RoutePlan update(RoutePlanDto plan_dto);

    void delete(String product_code,String route_code,List<String> code_list,List<String> will_be_del);

    AbstractResponse<RoutePlanShortRespDto> list(RoutePlanShortRequest request);
}