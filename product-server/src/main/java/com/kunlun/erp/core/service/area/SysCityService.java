package com.kunlun.erp.core.service.area;

import com.kunlun.erp.core.dto.area.CityDto;
import com.kunlun.erp.core.entity.SysCity;

import java.util.List;

/**
 * @InterfaceName SysCityService
 * @Description 城市服务接口
 * @Author Jm.zhang
 * @Date 2019/11/25 15:58
 * @Version 1.0
 **/
public interface SysCityService {

    List<CityDto> getListDto(Integer country_id,Integer province_id);

    SysCity getCityByCityId(Integer city_id);

    Boolean isExist(Integer city_id,Integer country_id,Integer province_id);
}