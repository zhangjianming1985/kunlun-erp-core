package com.kunlun.erp.core.service.product;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.entity.ProductCategory;

/**
 * @InterfaceName ProductCategoryService
 * @Description 产品类别服务接口
 * @Author Jm.zhang
 * @Date 2019/12/6 10:10
 * @Version 1.0
 **/
public interface ProductCategoryService {
    ProductCategory getRecordByCategoryCode(String category_code);

    AbstractResponse<ProductCategoryListRespDto> list (ProductCategoryListRequest request);

    AbstractResponse<ProductCategoryAddRespDto> add(ProductCategoryAddRequest request);

    AbstractResponse<ProductCategoryDetailRespDto> detail (ProductCategoryDetailRequest request);

    AbstractResponse<ProductCategoryUpdateRespDto> update (ProductCategoryUpdateRequest request);

    AbstractResponse<ProductCategoryDelRespDto> delete (ProductCategoryDelRequest request);

}