package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteResideDto;
import com.kunlun.erp.core.entity.RouteReside;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteResideMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code") String company_code);

    int deleteByGroupCodeAndResideCode(@Param("group_code") String group_code,@Param("reside_codes") List<String> reside_codes);


    int insertSelective(RouteReside record);

    RouteReside selectByPrimaryKey(Integer id);

    RouteReside selectByResideCode(@Param("reside_code") String reside_code);

    int updateByPrimaryKeySelective(RouteReside record);

    List<RouteResideDto> selectDtoByGroupCode(@Param("group_code") String group_code);



}