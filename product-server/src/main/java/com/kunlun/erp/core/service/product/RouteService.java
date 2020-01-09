package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.RouteBaseDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteInfo;

/**
 * @InterfaceName RouteService
 * @Description 线路服务接口
 * @Author Jm.zhang
 * @Date 2019/12/10 17:54
 * @Version 1.0
 **/
public interface RouteService {

    RouteInfo add (String product_code, AbstractResponse<UserInfoRespDto> user_info, RouteBaseDto route_base_info);

    RouteInfo update (String product_code,RouteBaseDto route_base_info);
}