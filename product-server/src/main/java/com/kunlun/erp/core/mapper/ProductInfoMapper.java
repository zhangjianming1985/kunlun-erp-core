package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.ProductCondition;
import com.kunlun.erp.core.dto.product.response.RouteProductListDto;
import com.kunlun.erp.core.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByProductCode(@Param("product_code") String product_code);

    int insertSelective(ProductInfo record);


    ProductInfo selectByPrimaryKey(Integer id);

    ProductInfo selectByProductCode(String product_code);


    int updateByPrimaryKeySelective(ProductInfo record);

    List<RouteProductListDto> selectByCondition(ProductCondition condition);

    int countByProductName(String product_name);

    int countByProductCategoryCode(@Param("product_category_code")String product_category_code);

}