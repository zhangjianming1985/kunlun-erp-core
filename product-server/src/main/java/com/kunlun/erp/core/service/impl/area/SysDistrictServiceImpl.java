package com.kunlun.erp.core.service.impl.area;

import com.kunlun.erp.core.dto.area.DistrictDto;
import com.kunlun.erp.core.entity.SysDistrict;
import com.kunlun.erp.core.mapper.SysDistrictMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.area.SysDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysDistrictServiceImpl
 * @Description 地区业务实现
 * @Author Jm.zhang
 * @Date 2019/11/25 16:20
 * @Version 1.0
 **/
@Service(value = "sys_district_service")
public class SysDistrictServiceImpl extends BaseService implements SysDistrictService {
    @Resource
    private SysDistrictMapper sys_district_dao;

    @Override
    public List<DistrictDto> getShortDto() {
        return sys_district_dao.selectShortDto();
    }

    @Override
    public SysDistrict getDistrictByDistrictId(Integer district_id) {
        return sys_district_dao.selectByPrimaryKey(district_id);
    }

    @Override
    public Boolean isExist(Integer district_id) {
        SysDistrict district = this.getDistrictByDistrictId(district_id);
        return district != null;
    }
}
