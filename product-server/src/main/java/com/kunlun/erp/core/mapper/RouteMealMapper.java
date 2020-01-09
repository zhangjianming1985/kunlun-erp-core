package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteMealDto;
import com.kunlun.erp.core.entity.RouteMeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMealMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code") String company_code);

    int deleteByGroupCodeAndMealCode(@Param("group_code") String group_code,@Param("meal_codes") List<String> meal_codes);

    int insertSelective(RouteMeal record);

    RouteMeal selectByPrimaryKey(Integer id);

    RouteMeal selectByMealCode(@Param("meal_code") String meal_code);

    int updateByPrimaryKeySelective(RouteMeal record);

    List<RouteMealDto> selectDtoByGroupCode(@Param("group_code") String group_code);

}