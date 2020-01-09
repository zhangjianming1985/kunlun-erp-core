package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.area.CountyDto;
import com.kunlun.erp.core.entity.SysCounty;

import java.util.List;

public interface SysCountyMapper {

    int insertSelective(SysCounty record);

    SysCounty selectByPrimaryKey(Integer county_id);

    List<CountyDto> selectListDto(Integer city_id);
}