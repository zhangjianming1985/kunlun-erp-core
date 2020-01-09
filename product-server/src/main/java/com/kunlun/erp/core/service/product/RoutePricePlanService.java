package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;

/**
 * @InterfaceName RoutePricePlanService
 * @Description 线路产品价格方案服务接口
 * @Author Jm.zhang
 * @Date 2019/12/16 19:08
 * @Version 1.0
 **/
public interface RoutePricePlanService {
    AbstractResponse<PricePlanAddRespDto> add(PricePlanAddRequest request);

    AbstractResponse<PricePlanNameUpdateRespDto> updatePricePlanName(PricePlanNameUpdateRequest request);

    AbstractResponse<PricePlanDetailRespDto> detail(PricePlanDetailRequest request);

    AbstractResponse<PricePlanDelRespDto> deletePricePlan(PricePlanDelRequest request);

    AbstractResponse<PriceDateDelRespDto> deletePriceDate(PriceDateDelRequest request);


    AbstractResponse<PricePlanCostDetailRespDto>  costDetail(PricePlanCostDetailRequest request);
}