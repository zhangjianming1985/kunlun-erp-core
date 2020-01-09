package com.kunlun.erp.core.service.area;

import com.kunlun.erp.core.dto.area.DistrictDto;
import com.kunlun.erp.core.entity.SysDistrict;

import java.util.List;

/**
 * @ClassName SysDistrictService
 * @Description 区域业务接口
 * @Author Jm.zhang
 * @Date 2019/11/25 16:19
 * @Version 1.0
 **/
public interface SysDistrictService {
    List<DistrictDto> getShortDto();

    SysDistrict getDistrictByDistrictId(Integer district_id);

    Boolean isExist(Integer district_id);
}
