package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteMotorcadeDto;
import com.kunlun.erp.core.entity.RouteMotorcade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMotorcadeMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code") String company_code);

    int deleteByGroupCodeAndMotorcadeCode(@Param("group_code") String group_code,@Param("motorcade_codes") List<String> motorcade_codes);

    int insertSelective(RouteMotorcade record);

    RouteMotorcade selectByPrimaryKey(Integer id);

    RouteMotorcade selectByMotorcadeCode(@Param("motorcade_code") String motorcade_code);

    int updateByPrimaryKeySelective(RouteMotorcade record);

    List<RouteMotorcadeDto> selectDtoByGroupCode(@Param("group_code") String group_code);


}