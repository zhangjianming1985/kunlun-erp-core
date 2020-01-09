package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.area.CountryDto;
import com.kunlun.erp.core.entity.SysCountry;

import java.util.List;

public interface SysCountryMapper {

    int insertSelective(SysCountry record);

    SysCountry selectByPrimaryKey(Integer country_id);

    List<CountryDto> selectListDto(Integer country_type);


}