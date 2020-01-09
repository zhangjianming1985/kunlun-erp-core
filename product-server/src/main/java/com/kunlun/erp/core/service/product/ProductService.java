package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.entity.ProductInfo;

/**
 * @InterfaceName ProductService
 * @Description 线路产品服务接口
 * @Author Jm.zhang
 * @Date 2019/12/9 18:28
 * @Version 1.0
 **/
public interface ProductService {

    AbstractResponse<RouteProductListRespDto> list(RouteProductListRequest request);

    AbstractResponse<RouteProductAddRespDto> add(RouteProductAddRequest request);

    AbstractResponse<RouteProductDetailRespDto> detail (RouteProductDetailRequest request);

    AbstractResponse<RouteProductEditRespDto> update(RouteProductEditRequest request);

    AbstractResponse<RouteProductCopyRespDto> copy(RouteProductCopyRequest request);

    AbstractResponse<RouteProductDelRespDto> delete(RouteProductDelRequest request);


    boolean isExist(String product_name);

    ProductInfo selectByProductCode(String product_code);
}