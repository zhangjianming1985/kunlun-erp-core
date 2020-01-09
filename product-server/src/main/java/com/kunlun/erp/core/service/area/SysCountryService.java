package com.kunlun.erp.core.service.area;

import com.kunlun.erp.core.dto.area.CountryDto;
import com.kunlun.erp.core.entity.SysCountry;

import java.util.List;

/**
 * @ClassName SysCountryService
 * @Description 国家数据服务接口
 * @Author Jm.zhang
 * @Date 2019/11/25 12:30
 * @Version 1.0
 **/
public interface SysCountryService {

    List<CountryDto> getListDto(Integer country_type);

    SysCountry getCountryByCountryId(Integer country_id);

    Boolean isExist(Integer country_id);


}
