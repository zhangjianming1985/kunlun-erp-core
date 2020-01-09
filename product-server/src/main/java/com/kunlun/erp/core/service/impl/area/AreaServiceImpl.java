package com.kunlun.erp.core.service.impl.area;

import com.kunlun.erp.core.common.cache.CacheKeyConstants;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.area.*;
import com.kunlun.erp.core.dto.area.linkage.AreaLink;
import com.kunlun.erp.core.dto.area.request.AreaReqDto;
import com.kunlun.erp.core.dto.area.request.AreaRequest;
import com.kunlun.erp.core.dto.area.response.AreaListRespDto;
import com.kunlun.erp.core.service.area.AreaService;
import com.kunlun.erp.core.service.BaseService;
import org.ehcache.Cache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AreaServiceImpl
 * @Description 地区区域服务实现
 * @Author Jm.zhang
 * @Date 2019/12/5 15:08
 * @Version 1.0
 **/
@Service(value = "area_service")
public class AreaServiceImpl extends BaseService implements AreaService {
    @Override
    public AbstractResponse<AreaListRespDto> list(AreaRequest request) {
        AreaReqDto req_body = request.getBody();
        AbstractResponse<AreaListRespDto> response = dtoFactory.createResponse(request.getHeader());
        AreaListRespDto resp_body = new AreaListRespDto();

        if (req_body.getArea_type()!=null){
            if (req_body.getArea_type() ==SysConstant.AreaType.foreign_country.getValue()){
                //境外国家
                List<CountryDto> country_list = sys_country_service.getListDto(req_body.getArea_type());
                resp_body.setCountry_list(country_list);
            }else if (req_body.getArea_type()== SysConstant.AreaType.district.getValue()){
                //中国区域数据
                List<DistrictDto> district_list = sys_district_service.getShortDto();
                resp_body.setDistrict_list(district_list);
            }else if (req_body.getArea_type() ==SysConstant.AreaType.province.getValue()){
                //所有的省份数据
                List<ProvinceDto> province_list = sys_province_service.getListDto(null);
                resp_body.setProvince_list(province_list);
            }else if (req_body.getArea_type() ==SysConstant.AreaType.all.getValue()){
                //所有数据
                Cache<String,Object> cache   = cacheManager.getCache(CacheKeyConstants.full_area_data,String.class,Object.class);
                AreaLink link_data = (AreaLink)cache.get(CacheKeyConstants.full_area_data);
                resp_body.setArea_link_data(link_data);
            }

        }
        if (req_body.getDistrict_id()!= null){
            //获取区域的省份数据
            List<ProvinceDto> province_list = sys_province_service.getListDto(req_body.getDistrict_id());
            resp_body.setProvince_list(province_list);
        }

        if (req_body.getCountry_id() != null){
            //境外国家ID, 获取国家的城市数据
            List<CityDto> city_list = sys_city_service.getListDto(req_body.getCountry_id(),null);
            resp_body.setCity_list(city_list);
        }

        if (req_body.getProvince_id() != null){
            //获取省份的城市数据
            List<CityDto> city_list = sys_city_service.getListDto(null,req_body.getProvince_id());
            resp_body.setCity_list(city_list);
        }
        if (req_body.getCity_id()!=null){
            //获取城市的区县数据
            List<CountyDto> county_list = sys_county_service.getListDto(req_body.getCity_id());
            resp_body.setCounty_list(county_list);
        }
        response.setBody(resp_body);

        return response;
    }
}
