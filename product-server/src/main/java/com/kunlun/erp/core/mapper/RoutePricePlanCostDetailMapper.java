package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.RouteCostCondition;
import com.kunlun.erp.core.dto.product.RoutePriceCostDetailDto;
import com.kunlun.erp.core.entity.RoutePricePlanCostDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePricePlanCostDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByProductCode(@Param("product_code")String product_code);

    int deleteByCondition(RouteCostCondition condition);

    int insertSelective(RoutePricePlanCostDetail record);

    RoutePricePlanCostDetail selectByPrimaryKey(Integer id);

    RoutePricePlanCostDetail selectByCostCode(@Param("cost_code") String cost_code);

    List<RoutePriceCostDetailDto> selectDtoByPricePlanCodeAndDate(@Param("price_plan_code") String price_plan_code,@Param("departure_date") String departure_date);

    int updateByPrimaryKeySelective(RoutePricePlanCostDetail record);

 }