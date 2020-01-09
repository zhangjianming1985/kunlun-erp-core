package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.area.CityDto;
import com.kunlun.erp.core.entity.SysCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCityMapper {

    int insertSelective(SysCity record);

    SysCity selectByPrimaryKey(Integer city_id);

    List<CityDto> selectListDto(@Param("country_id") Integer country_id, @Param("province_id") Integer province_id);
}