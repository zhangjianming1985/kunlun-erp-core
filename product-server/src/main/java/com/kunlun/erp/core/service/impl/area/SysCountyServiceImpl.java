package com.kunlun.erp.core.service.impl.area;

import com.kunlun.erp.core.dto.area.CountyDto;
import com.kunlun.erp.core.entity.SysCounty;
import com.kunlun.erp.core.mapper.SysCountyMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.area.SysCountyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysCountyServiceImpl
 * @Description 区县服务实现
 * @Author Jm.zhang
 * @Date 2019/11/25 16:32
 * @Version 1.0
 **/
@Service(value = "sys_county_service")
public class SysCountyServiceImpl extends BaseService implements SysCountyService {
    @Resource
    private SysCountyMapper sys_county_dao;
    @Override
    public List<CountyDto> getListDto(Integer city_id) {
        return sys_county_dao.selectListDto(city_id);
    }

    @Override
    public SysCounty getCountyByCountyId(Integer county_id) {
        return sys_county_dao.selectByPrimaryKey(county_id);
    }

    @Override
    public Boolean isExist(Integer county_id, Integer city_id) {
        SysCounty county = this.getCountyByCountyId(county_id);
        if (county == null) return false;
        if (city_id!= null){
            return county.getCity_id().toString().equalsIgnoreCase(city_id.toString());
        }
        return true;
    }
}
