package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteOtherDto;
import com.kunlun.erp.core.entity.RouteOther;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteOtherMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code") String company_code);

    int deleteByGroupCodeAndOtherCode(@Param("group_code") String group_code,@Param("other_codes") List<String> other_codes);

    int insertSelective(RouteOther record);

    RouteOther selectByPrimaryKey(Integer id);

    RouteOther selectByOtherCode(@Param("other_code") String other_code);

    int updateByPrimaryKeySelective(RouteOther record);

    List<RouteOtherDto> selectDtoByGroupCode(@Param("group_code") String group_code);


}