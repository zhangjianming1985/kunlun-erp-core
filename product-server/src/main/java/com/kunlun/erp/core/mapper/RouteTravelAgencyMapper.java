package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyDto;
import com.kunlun.erp.core.entity.RouteTravelAgency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteTravelAgencyMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RouteTravelAgency record);

    RouteTravelAgency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RouteTravelAgency record);

    List<RouteTravelAgencyDto> selectDtoByGroupCode(@Param("group_code")String group_code);

    RouteTravelAgency selectByTravelAgencyCode(@Param("travel_code")String travel_code);

    int deleteByGroupCode(@Param("group_code")String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByGroupCodeAndTravelAgencyCode(@Param("group_code")String group_code,@Param("travel_codes")List<String>travel_codes);

    int deleteByCompanyCode(@Param("company_code")String company_code);

}