package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.RoutePlanNodeCondition;
import com.kunlun.erp.core.entity.RoutePlanNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePlanNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByProductCode(@Param("product_code")String product_code);

    void deleteByCondition(RoutePlanNodeCondition condition);

    int insertSelective(RoutePlanNode record);

    RoutePlanNode selectByPrimaryKey(Integer id);

    RoutePlanNode selectByNodeCode(String node_code);

    List<RoutePlanNode> selectByRoutePlanCode(String route_plan_code);

    int updateByPrimaryKeySelective(RoutePlanNode record);

}