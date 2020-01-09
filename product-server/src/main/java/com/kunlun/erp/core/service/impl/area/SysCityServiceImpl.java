package com.kunlun.erp.core.service.impl.area;

import com.kunlun.erp.core.dto.area.CityDto;
import com.kunlun.erp.core.entity.SysCity;
import com.kunlun.erp.core.mapper.SysCityMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.area.SysCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysCityServiceImpl
 * @Description 城市服务接口实现类
 * @Author Jm.zhang
 * @Date 2019/11/25 15:59
 * @Version 1.0
 **/
@Service(value = "sys_city_service")
public class SysCityServiceImpl extends BaseService implements SysCityService {
    @Resource
    private SysCityMapper sys_city_dao;


    @Override
    public List<CityDto> getListDto(Integer country_id,Integer province_id) {
        return sys_city_dao.selectListDto(country_id,province_id);
    }

    @Override
    public SysCity getCityByCityId(Integer city_id) {
        return sys_city_dao.selectByPrimaryKey(city_id);
    }

    @Override
    public Boolean isExist(Integer city_id,Integer country_id,Integer province_id) {
        SysCity city = this.getCityByCityId(city_id);
        if (city == null) return false;
        if (country_id != null){
            if (!city.getCountry_id().toString().equalsIgnoreCase(country_id.toString()))return false;
        }
       if (province_id!=null){
           return city.getProvince_id().toString().equalsIgnoreCase(province_id.toString());
       }
        return true;
    }
}
