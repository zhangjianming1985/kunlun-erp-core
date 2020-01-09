package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.area.DistrictDto;
import com.kunlun.erp.core.entity.SysDistrict;

import java.util.List;

public interface SysDistrictMapper {
    int insertSelective(SysDistrict record);

    SysDistrict selectByPrimaryKey(Integer district_id);

    List<DistrictDto> selectShortDto();
}