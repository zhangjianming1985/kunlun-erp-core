package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.*;
import com.kunlun.erp.core.dto.routeHall.request.HallProductDetailRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductListRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductStateUpdateRequest;
import com.kunlun.erp.core.dto.routeHall.response.HallProductDetailRespDto;
import com.kunlun.erp.core.dto.routeHall.response.HallProductListRespDto;

/**
 * @InterfaceName RouteHallService
 * @Description 报名大厅服务接口
 * @Author Jm.zhang
 * @Date 2019/12/18 12:11
 * @Version 1.0
 **/
public interface RouteHallService {
    void processByPrice(PriceUpdateNotifyDto notify_dto);

    void processByProduct(ProductUpdateNotifyDto notify_dto);

    void processByPricePlan(PricePlanDeleteNotifyDto notify_dto);

    int processByPriceDate(PriceDateDeleteNotifyDto notify_dto);

    void processByPerson(PersonUpdateNotifyDto notify_dto);

    AbstractResponse<HallProductListRespDto> list(HallProductListRequest request);

    AbstractResponse<HallProductDetailRespDto> detail(HallProductDetailRequest request);

    AbstractResponse updateState(HallProductStateUpdateRequest request);


}