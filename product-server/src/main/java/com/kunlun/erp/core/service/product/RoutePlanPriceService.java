package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.RoutePlanBasePriceDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RoutePlanBasePrice;

import java.util.List;

/**
 * @InterfaceName RoutePlanPriceService
 * @Description 线路计划价格服务接口
 * @Author Jm.zhang
 * @Date 2019/12/10 18:24
 * @Version 1.0
 **/
public interface RoutePlanPriceService {

    RoutePlanBasePrice add (String product_code, String route_code, String plan_code, AbstractResponse<UserInfoRespDto> user_info, RoutePlanBasePriceDto price_dto);

    RoutePlanBasePrice update ( RoutePlanBasePriceDto price_dto);

    void delete(String plan_code, List<String> price_list);
}