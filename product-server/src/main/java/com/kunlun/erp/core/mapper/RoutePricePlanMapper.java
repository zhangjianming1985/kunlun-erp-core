package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.product.PricePlanDto;
import com.kunlun.erp.core.entity.RoutePricePlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePricePlanMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByPricePlanCode(@Param("price_plan_code") String price_plan_code);

    int deleteByProductCode(@Param("product_code") String product_code);

    int deleteByRoutePlan(@Param("route_plan_list") List<String> price_plan_code);

    int insertSelective(RoutePricePlan record);

    int countByProductCode(@Param("product_code") String product_code);

    int countByProductCodeAndPlanName(@Param("product_code") String product_code,@Param("price_plan_name") String price_plan_name);

    RoutePricePlan selectByPrimaryKey(Integer id);

    RoutePricePlan selectByPricePlanCode(@Param("price_plan_code")String price_plan_code);

    int updateByPrimaryKeySelective(RoutePricePlan record);

    List<PricePlanDto> selectDtoByProductCode(@Param("product_code")String product_code);

}