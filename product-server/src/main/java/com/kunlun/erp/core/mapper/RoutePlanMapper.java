package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.RoutePlanCondition;
import com.kunlun.erp.core.dto.product.RoutePlanShortDto;
import com.kunlun.erp.core.entity.RoutePlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePlanMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByProductCode(@Param("product_code") String product_code);

    void deleteByCondition(RoutePlanCondition condition);

    int insertSelective(RoutePlan record);

    RoutePlan selectByPrimaryKey(Integer id);

    RoutePlan selectByPlanCode(String plan_code);

    List<RoutePlan> selectByRouteCode(String route_code);

    List<RoutePlanShortDto> selectShortDtoByProductCode(@Param("product_code") String product_code);

    List<String> selectPlanCodeByProductCode(@Param("product_code") String product_code);

    int updateByPrimaryKeySelective(RoutePlan record);

}