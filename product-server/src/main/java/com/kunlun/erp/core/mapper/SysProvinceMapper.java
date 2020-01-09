package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.area.ProvinceDto;
import com.kunlun.erp.core.entity.SysProvince;

import java.util.List;

public interface SysProvinceMapper {

    int insertSelective(SysProvince record);

    SysProvince selectByPrimaryKey(Integer province_id);

    List<ProvinceDto> selectListDto(Integer district_id);

}