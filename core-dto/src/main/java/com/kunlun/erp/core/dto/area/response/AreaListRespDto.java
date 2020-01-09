package com.kunlun.erp.core.dto.area.response;

import com.kunlun.erp.core.dto.area.*;
import com.kunlun.erp.core.dto.area.linkage.AreaLink;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName AreaListRespDto
 * @Description 响应国家数据列表
 * @Author Jm.zhang
 * @Date 2019/12/5 9:46
 * @Version 1.0
 **/
@ApiModel(description = "响应国家列表")
public class AreaListRespDto {

    private AreaLink area_link_data;


    private List<CountryDto> country_list;

    private List<DistrictDto> district_list;

    private List<ProvinceDto> province_list;

    private List<CityDto> city_list;

    private List<CountyDto> county_list;

    public List<CountryDto> getCountry_list() {
        return country_list;
    }

    public void setCountry_list(List<CountryDto> country_list) {
        this.country_list = country_list;
    }

    public List<DistrictDto> getDistrict_list() {
        return district_list;
    }

    public void setDistrict_list(List<DistrictDto> district_list) {
        this.district_list = district_list;
    }

    public List<ProvinceDto> getProvince_list() {
        return province_list;
    }

    public void setProvince_list(List<ProvinceDto> province_list) {
        this.province_list = province_list;
    }

    public List<CityDto> getCity_list() {
        return city_list;
    }

    public void setCity_list(List<CityDto> city_list) {
        this.city_list = city_list;
    }

    public List<CountyDto> getCounty_list() {
        return county_list;
    }

    public void setCounty_list(List<CountyDto> county_list) {
        this.county_list = county_list;
    }

    public AreaLink getArea_link_data() {
        return area_link_data;
    }

    public void setArea_link_data(AreaLink area_link_data) {
        this.area_link_data = area_link_data;
    }
}
