package com.kunlun.erp.core.component;

import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.service.area.*;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RoutePlanComponent
 * @Description 线路计划组件
 * @Author Jm.zhang
 * @Date 2019/12/10 16:12
 * @Version 1.0
 **/
@Component(value = "component_roue_plan")
public class RoutePlanComponent {
    @Resource(name = "sys_country_service")
    protected SysCountryService sys_country_service;
    @Resource(name = "sys_province_service")
    protected SysProvinceService sys_province_service;
    @Resource(name = "sys_city_service")
    protected SysCityService sys_city_service;
    @Resource(name = "sys_county_service")
    protected SysCountyService sys_county_service;
    @Resource(name = "sys_district_service")
    protected SysDistrictService sys_district_service;
    @Resource(name = "area_validator")
    protected AreaValidator area_validator;

    /**
     * 重设出发地
     * @param new_plan_record
     * @param departure_area_info
     * @return
     */
    public RoutePlan BuildDepartureArea(RoutePlan new_plan_record, AreaDto departure_area_info){
        //出发地
        SysCountry country = sys_country_service.getCountryByCountryId(departure_area_info.getCountry_id());
        new_plan_record.setDeparture_country_id(country.getCountry_id());
        new_plan_record.setDeparture_country_name(country.getZh_name());

        SysCity city = sys_city_service.getCityByCityId(departure_area_info.getCity_id());
        new_plan_record.setDeparture_city_id(city.getCity_id());
        new_plan_record.setDeparture_city_name(city.getZh_name());
        if (area_validator.isChina(departure_area_info.getCountry_id())){
                SysDistrict district = sys_district_service.getDistrictByDistrictId(departure_area_info.getDistrict_id());
                new_plan_record.setDeparture_district_id(district.getDistrict_id());
                new_plan_record.setDeparture_district_name(district.getZh_name());
                SysProvince province = sys_province_service.getProvinceByProvinceId(departure_area_info.getProvince_id());
                new_plan_record.setDeparture_province_id(province.getProvince_id());
                new_plan_record.setDeparture_province_name(province.getZh_name());
                SysCounty county = sys_county_service.getCountyByCountyId(departure_area_info.getCounty_id());
                new_plan_record.setDeparture_county_id(county.getCounty_id());
                new_plan_record.setDeparture_county_name(county.getZh_name());
        }else{
            new_plan_record.setDeparture_district_id(null);
            new_plan_record.setDeparture_district_name(null);
            new_plan_record.setDeparture_province_id(null);
            new_plan_record.setDeparture_province_name(null);
            new_plan_record.setDeparture_county_id(null);
            new_plan_record.setDeparture_county_name(null);
        }
       return new_plan_record;
    }

    /**
     * 重设目的地
     * @param new_plan_record
     * @param destination_area_info
     * @return
     */
    public RoutePlan BuildDestinationArea(RoutePlan new_plan_record, AreaDto destination_area_info){
        SysCountry country = sys_country_service.getCountryByCountryId(destination_area_info.getCountry_id());
        new_plan_record.setDestination_country_id(country.getCountry_id());
        new_plan_record.setDestination_country_name(country.getZh_name());

        SysCity city = sys_city_service.getCityByCityId(destination_area_info.getCity_id());
        new_plan_record.setDestination_city_id(city.getCity_id());
        new_plan_record.setDestination_city_name(city.getZh_name());
        if (area_validator.isChina(destination_area_info.getCountry_id())){
            SysDistrict district = sys_district_service.getDistrictByDistrictId(destination_area_info.getDistrict_id());
            new_plan_record.setDestination_district_id(district.getDistrict_id());
            new_plan_record.setDestination_district_name(district.getZh_name());
            SysProvince province = sys_province_service.getProvinceByProvinceId(destination_area_info.getProvince_id());
            new_plan_record.setDestination_province_id(province.getProvince_id());
            new_plan_record.setDestination_province_name(province.getZh_name());
            SysCounty county = sys_county_service.getCountyByCountyId(destination_area_info.getCounty_id());
            new_plan_record.setDestination_county_id(county.getCounty_id());
            new_plan_record.setDestination_county_name(county.getZh_name());
        }else{
            new_plan_record.setDestination_district_id(null);
            new_plan_record.setDestination_district_name(null);
            new_plan_record.setDestination_province_id(null);
            new_plan_record.setDestination_province_name(null);
            new_plan_record.setDestination_county_id(null);
            new_plan_record.setDestination_county_name(null);
        }
        return new_plan_record;
    }

    /**
     * 重设成团地点
     * @param new_plan_record
     * @param rendezvous_area_info
     * @return
     */
    public RoutePlan BuildRendezvousArea(RoutePlan new_plan_record, AreaDto rendezvous_area_info){
        SysCountry country = sys_country_service.getCountryByCountryId(rendezvous_area_info.getCountry_id());
        new_plan_record.setRendezvous_country_id(country.getCountry_id());
        new_plan_record.setRendezvous_country_name(country.getZh_name());

        SysCity city = sys_city_service.getCityByCityId(rendezvous_area_info.getCity_id());
        new_plan_record.setRendezvous_city_id(city.getCity_id());
        new_plan_record.setRendezvous_city_name(city.getZh_name());
        if (area_validator.isChina(rendezvous_area_info.getCountry_id())){
            SysDistrict district = sys_district_service.getDistrictByDistrictId(rendezvous_area_info.getDistrict_id());
            new_plan_record.setRendezvous_district_id(district.getDistrict_id());
            new_plan_record.setRendezvous_district_name(district.getZh_name());
            SysProvince province = sys_province_service.getProvinceByProvinceId(rendezvous_area_info.getProvince_id());
            new_plan_record.setRendezvous_province_id(province.getProvince_id());
            new_plan_record.setRendezvous_province_name(province.getZh_name());
            SysCounty county = sys_county_service.getCountyByCountyId(rendezvous_area_info.getCounty_id());
            new_plan_record.setRendezvous_county_id(county.getCounty_id());
            new_plan_record.setRendezvous_county_name(county.getZh_name());
        }else{
            new_plan_record.setRendezvous_district_id(null);
            new_plan_record.setRendezvous_district_name(null);
            new_plan_record.setRendezvous_province_id(null);
            new_plan_record.setRendezvous_province_name(null);
            new_plan_record.setRendezvous_county_id(null);
            new_plan_record.setRendezvous_county_name(null);
        }
        return new_plan_record;
    }

    public String areaDepartureToStr(RoutePlan plan_record){
        StringBuffer sb = new StringBuffer(plan_record.getDeparture_country_name());

        if (StringUtils.isNotBlank(plan_record.getDeparture_district_name())){
            sb.append("/"+plan_record.getDeparture_district_name());
        }
        if (StringUtils.isNotBlank(plan_record.getDeparture_province_name())){
            sb.append("/"+plan_record.getDeparture_province_name());
        }
        if (StringUtils.isNotBlank(plan_record.getDeparture_city_name())){
            sb.append("/"+plan_record.getDeparture_city_name());
        }
        if (StringUtils.isNotBlank(plan_record.getDeparture_county_name())){
            sb.append("/"+plan_record.getDeparture_county_name());
        }
        return sb.toString();
    }


    public String areaDestinationToStr(RoutePlan plan_record){
        StringBuffer sb = new StringBuffer(plan_record.getDestination_country_name());

        if (StringUtils.isNotBlank(plan_record.getDestination_district_name())){
            sb.append("/"+plan_record.getDestination_district_name());
        }
        if (StringUtils.isNotBlank(plan_record.getDestination_province_name())){
            sb.append("/"+plan_record.getDestination_province_name());
        }
        if (StringUtils.isNotBlank(plan_record.getDestination_city_name())){
            sb.append("/"+plan_record.getDestination_city_name());
        }
        if (StringUtils.isNotBlank(plan_record.getDestination_county_name())){
            sb.append("/"+plan_record.getDestination_county_name());
        }
        return sb.toString();
    }

    public String areaRendezvousToStr(RoutePlan plan_record){
        StringBuffer sb = new StringBuffer(plan_record.getRendezvous_country_name());

        if (StringUtils.isNotBlank(plan_record.getRendezvous_district_name())){
            sb.append("/"+plan_record.getRendezvous_district_name());
        }
        if (StringUtils.isNotBlank(plan_record.getRendezvous_province_name())){
            sb.append("/"+plan_record.getRendezvous_province_name());
        }
        if (StringUtils.isNotBlank(plan_record.getRendezvous_city_name())){
            sb.append("/"+plan_record.getRendezvous_city_name());
        }
        if (StringUtils.isNotBlank(plan_record.getRendezvous_county_name())){
            sb.append("/"+plan_record.getRendezvous_county_name());
        }
        return sb.toString();
    }
}
