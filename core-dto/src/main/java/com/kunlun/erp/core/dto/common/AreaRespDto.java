package com.kunlun.erp.core.dto.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName AreaListRespDto
 * @Description 区域响应
 * @Author Jm.zhang
 * @Date 2019/11/28 15:59
 * @Version 1.0
 **/
public class AreaRespDto extends AreaDto{

    @ApiModelProperty(value = "国家名称",example = "中国")
    private String country_name;

    @ApiModelProperty(value = "区域名称",example = "西南")
    private String district_name;

    @ApiModelProperty(value = "省名称",example = "甘肃")
    private String province_name;

    @ApiModelProperty(value = "城市名称",example = "白银")
    private String city_name;
    @ApiModelProperty(value = "区县名称",example = "白银区")
    private String county_name;

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }
}
