package com.kunlun.erp.core.service.area;

import com.kunlun.erp.core.dto.area.ProvinceDto;
import com.kunlun.erp.core.entity.SysProvince;

import java.util.List;

/**
 * @InterfaceName SysProvinceService
 * @Description 省份业务接口
 * @Author Jm.zhang
 * @Date 2019/11/25 15:28
 * @Version 1.0
 **/
public interface SysProvinceService {
    List<ProvinceDto> getListDto(Integer district_id);

    SysProvince getProvinceByProvinceId(Integer province_id);

    Boolean isExist(Integer province_id);
}