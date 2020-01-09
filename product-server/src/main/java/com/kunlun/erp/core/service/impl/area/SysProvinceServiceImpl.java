package com.kunlun.erp.core.service.impl.area;

import com.kunlun.erp.core.dto.area.ProvinceDto;
import com.kunlun.erp.core.entity.SysProvince;
import com.kunlun.erp.core.mapper.SysProvinceMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.area.SysProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysProvinceServiceImpl
 * @Description 省份服务接口实现
 * @Author Jm.zhang
 * @Date 2019/11/25 15:34
 * @Version 1.0
 **/
@Service(value = "sys_province_service")
public class SysProvinceServiceImpl extends BaseService implements SysProvinceService {
    @Resource
    private SysProvinceMapper sys_province_dao;


    @Override
    public List<ProvinceDto> getListDto(Integer district_id) {
        return sys_province_dao.selectListDto(district_id);
    }

    @Override
    public SysProvince getProvinceByProvinceId(Integer province_id) {
        return sys_province_dao.selectByPrimaryKey(province_id);
    }

    @Override
    public Boolean isExist(Integer province_id) {
        SysProvince  province = this.getProvinceByProvinceId(province_id);
        return province != null;
    }
}
