package com.kunlun.erp.core.service.area;

import com.kunlun.erp.core.dto.area.CountyDto;
import com.kunlun.erp.core.entity.SysCounty;

import java.util.List;

/**
 * @ClassName SysCountyService
 * @Description 区县服务接口
 * @Author Jm.zhang
 * @Date 2019/11/25 16:31
 * @Version 1.0
 **/
public interface SysCountyService {
    List<CountyDto> getListDto(Integer city_id);

    SysCounty getCountyByCountyId(Integer county_id);

    Boolean isExist(Integer county_id,Integer city_id);
}
