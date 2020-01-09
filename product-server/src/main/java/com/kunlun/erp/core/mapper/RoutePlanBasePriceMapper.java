package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.RoutePlanPriceCondition;
import com.kunlun.erp.core.entity.RoutePlanBasePrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePlanBasePriceMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByProductCode(@Param("product_code") String product_code);

    void deleteByCondition(RoutePlanPriceCondition condition);

    int insertSelective(RoutePlanBasePrice record);

    RoutePlanBasePrice selectByPrimaryKey(Integer id);

    RoutePlanBasePrice selectByPriceCode(String price_code);

    List<RoutePlanBasePrice> selectByRoutePlanCode(String route_plan_code);

    int updateByPrimaryKeySelective(RoutePlanBasePrice record);


}