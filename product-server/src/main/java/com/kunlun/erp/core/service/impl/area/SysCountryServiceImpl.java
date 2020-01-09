package com.kunlun.erp.core.service.impl.area;

import com.kunlun.erp.core.dto.area.CountryDto;
import com.kunlun.erp.core.entity.SysCountry;
import com.kunlun.erp.core.mapper.SysCountryMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.area.SysCountryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysCountryServiceImpl
 * @Description 国家服务
 * @Author Jm.zhang
 * @Date 2019/11/25 12:35
 * @Version 1.0
 **/
@Service(value = "sys_country_service")
public class SysCountryServiceImpl extends BaseService implements SysCountryService {
    @Resource
    private SysCountryMapper sys_country_dao;
    @Override
    public List<CountryDto> getListDto(Integer country_type) {
        return sys_country_dao.selectListDto(country_type);
    }

    @Override
    public SysCountry getCountryByCountryId(Integer country_id) {
        return sys_country_dao.selectByPrimaryKey(country_id);
    }

    @Override
    public Boolean isExist(Integer country_id) {
        SysCountry country = this.getCountryByCountryId(country_id);
        return country != null;
    }



}
