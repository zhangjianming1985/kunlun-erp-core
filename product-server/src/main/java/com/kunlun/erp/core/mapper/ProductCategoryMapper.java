package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.product.ProductCategoryDto;
import com.kunlun.erp.core.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByCategoryCode(@Param("category_code")String category_code);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    ProductCategory selectByCategoryCode(String category_code);

    int updateByPrimaryKeySelective(ProductCategory record);

    List<ProductCategoryDto> selectDtoList(@Param("category_name") String category_name,@Param("category_state")Integer category_state);


}