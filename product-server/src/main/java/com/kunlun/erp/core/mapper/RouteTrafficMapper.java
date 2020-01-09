package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteTrafficDto;
import com.kunlun.erp.core.entity.RouteTraffic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteTrafficMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RouteTraffic record);

    int deleteByCompanyCode(@Param("company_code")String company_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    RouteTraffic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RouteTraffic record);

    List<RouteTrafficDto> selectDtoByGroupCode(@Param("group_code") String group_code);

    RouteTraffic selectByTrafficCode(@Param("traffic_code")String traffic_code);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeAndTrafficCode(@Param("group_code") String group_code,@Param("traffic_codes") List<String> traffic_codes);
}