package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.product.RoutePricePlanDetailDto;
import com.kunlun.erp.core.entity.RoutePricePlanDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePricePlanDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByPricePlanCode(@Param("price_plan_code") String price_plan_code);

    int deleteByProductCode(@Param("product_code") String product_code);

    int deleteByPricePlanCodeAndDate(@Param("price_plan_code") String price_plan_code,@Param("departure_date") List<String> departure_date);

    int deleteByRoutePlan(@Param("route_plan_list") List<String> route_plan_list);

    List<String> selectPriceCodeByPricePlanCodeAndDate(@Param("price_plan_code") String price_plan_code,@Param("departure_date") List<String> departure_date);

    List<String> selectByPricePlanCode(@Param("price_plan_code") String price_plan_code);

    List<String> selectPriceCodeByRoutePlan(@Param("route_plan_list") List<String> route_plan_list);

    int insertSelective(RoutePricePlanDetail record);

    RoutePricePlanDetail selectByPrimaryKey(Integer id);

    RoutePricePlanDetail selectByPricePlanCodeAndDate(@Param("price_plan_code") String price_plan_code,@Param("departure_date") String departure_date);

    int updateByPrimaryKeySelective(RoutePricePlanDetail record);

    List<RoutePricePlanDetailDto> selectDtoByPricePlanCode(@Param("price_plan_code")String price_plan_code);

}