package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteGuidesDto;
import com.kunlun.erp.core.entity.RouteGuides;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteGuidesMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code") String company_code);

    int deleteByGroupCodeAndGuideCode(@Param("group_code") String group_code,@Param("guides_codes") List<String> guides_codes);

    int insertSelective(RouteGuides record);

    RouteGuides selectByPrimaryKey(Integer id);

    RouteGuides selectByGuideCode(String guide_code);

    int updateByPrimaryKeySelective(RouteGuides record);

    List<RouteGuidesDto> selectDtoByGroupCode(@Param("group_code") String group_code);

}