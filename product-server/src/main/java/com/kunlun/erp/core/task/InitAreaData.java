package com.kunlun.erp.core.task;

import com.kunlun.erp.core.common.cache.CacheKeyConstants;
import com.kunlun.erp.core.dto.area.*;
import com.kunlun.erp.core.dto.area.linkage.*;
import com.kunlun.erp.core.service.area.*;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName InitAreaData
 * @Description 初始化封装区域数据，并放置缓存
 * @Author Jm.zhang
 * @Date 2019/11/25 12:23
 * @Version 1.0
 **/
@Component
public class InitAreaData implements ApplicationRunner {
    @Resource(name = "sys_country_service")
    private SysCountryService sys_country_service;
    @Resource(name = "sys_province_service")
    private SysProvinceService sys_province_service;
    @Resource(name = "sys_city_service")
    private SysCityService sys_city_service;
    @Resource(name = "sys_district_service")
    private SysDistrictService sys_district_service;
    @Resource(name = "sys_county_service")
    private SysCountyService sys_county_service;
    @Resource
    protected CacheManager cacheManager;

    @Override
    public void run(ApplicationArguments args) {
        List<CountryDto> country_list = sys_country_service.getListDto(null);
        List<ProvinceDto> province_list = sys_province_service.getListDto(null);
        List<CityDto> city_list = sys_city_service.getListDto(null,null);
        List<DistrictDto> district_list = sys_district_service.getShortDto();
        List<CountyDto> county_list = sys_county_service.getListDto(null);
        //封装境外地区数据：境外线路【国家--城市】
        ForeignAreaLink link_foreign_area = new ForeignAreaLink();
        List<CountryLink> link_foreign_country = new ArrayList<>();
        for (CountryDto country_dto : country_list){
            //循环国家
            if (country_dto.getCountry_id()!=240){
                //非中国
                CountryLink foreign_country = new CountryLink();
                foreign_country.setCountry_id(country_dto.getCountry_id());
                foreign_country.setZh_name(country_dto.getZh_name());
                foreign_country.setFirst_letter(country_dto.getFirst_letter());
                List<CityDto> foreign_city = new ArrayList<>();
                for (CityDto city_dto : city_list){
                    //循环城市
                    if (city_dto.getCountry_id().toString().equals(foreign_country.getCountry_id().toString())){
                        foreign_city.add(city_dto);
                    }
                }
                foreign_country.setCity_data(foreign_city);
                link_foreign_country.add(foreign_country);
            }

        }
        link_foreign_area.setCountry_data(link_foreign_country);



        //封装境内区域联动：大区-省份--城市--区县
        InternalAreaLink link_internal_area = new InternalAreaLink();
        //大区列表
        List<DistrictLink> link_district_list = new ArrayList<>();
        for (DistrictDto district_dto : district_list){
            //循环大区
            DistrictLink link_district_dto = new DistrictLink();
            link_district_dto.setDistrict_id(district_dto.getDistrict_id());
            link_district_dto.setCountry_id(district_dto.getCountry_id());
            link_district_dto.setZh_name(district_dto.getZh_name());
            //省列表
            List<ProvinceLink> link_province_list = new ArrayList<>();
            for (ProvinceDto province_dto : province_list){
                //循环省
                if (province_dto.getDistrict_id().toString().equals(link_district_dto.getDistrict_id().toString())){
                    ProvinceLink link_province_dto = new ProvinceLink();
                    link_province_dto.setProvince_id(province_dto.getProvince_id());
                    link_province_dto.setDistrict_id(province_dto.getDistrict_id());
                    link_province_dto.setFirst_letter(province_dto.getFirst_letter());
                    link_province_dto.setZh_name(province_dto.getZh_name());
                    //城市列表

                    List<CityLink> link_city_list = new ArrayList<>();
                    //移除非中国城市
                    List<CityDto> china_city = new ArrayList<>();
                    for (CityDto city_dto :city_list){
                        if (city_dto.getCountry_id()==240){
                            china_city.add(city_dto);
                        }
                    }

                    for (CityDto city_dto :china_city){
                        //循环市
                        if (city_dto.getProvince_id().toString().equals(link_province_dto.getProvince_id().toString())){
                            CityLink link_city_dto = new CityLink();
                            link_city_dto.setCity_id(city_dto.getCity_id());
                            link_city_dto.setProvince_id(city_dto.getProvince_id());
                            link_city_dto.setZh_name(city_dto.getZh_name());
                            link_city_dto.setFirst_letter(city_dto.getFirst_letter());
                            //区县列表
                            List<CountyDto> link_county_list = new ArrayList<>();
                            for (CountyDto county_dto : county_list){
                                //循环区县
                                if (county_dto.getCity_id().toString().equals(link_city_dto.getCity_id().toString())){
                                    //区县DTO 封装入区县列表
                                    link_county_list.add(county_dto);
                                }
                            }
                            //区县列表封装入城市DTO
                            link_city_dto.setCounty_data(link_county_list);
                            //城市DTO 封装入城市列表
                            link_city_list.add(link_city_dto);
                        }

                    }
                    //城市列表封装入省DTO
                    link_province_dto.setCity_data(link_city_list);
                    //省DTO封装入省列表
                    link_province_list.add(link_province_dto);
                }
            }
            //省列表封装入大区DTO
            link_district_dto.setProvince_data(link_province_list);
            //大区DTO封装入大区列表
            link_district_list.add(link_district_dto);
        }
        //大区列表封装入大区数据DTO
        link_internal_area.setDistrict_data(link_district_list);
        AreaLink response = new AreaLink();
        response.setInternal_data(link_internal_area);
        response.setForeign_data(link_foreign_area);
        this.putToCache(response);

    }

    /**
     * 区域联动数据放入缓存
     * @param response
     */
    private void putToCache(AreaLink response){
        Cache<String,Object> cache   = cacheManager.getCache(CacheKeyConstants.full_area_data,String.class,Object.class);
        cache.put(CacheKeyConstants.full_area_data,response);
//        LogUtil.writeLogInfo(this.getClass().getName(),"putToCache()","put_cache key : "+CacheKeyConstants.full_area_data,JsonUtil.toStr(response), "");

    }

}
