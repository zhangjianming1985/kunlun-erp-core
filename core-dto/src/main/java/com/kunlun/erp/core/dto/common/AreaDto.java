package com.kunlun.erp.core.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName AreaDto
 * @Description 地区信息
 * @Author Jm.zhang
 * @Date 2019/11/18 14:39
 * @Version 1.0
 **/
@ApiModel(description = "区域信息")
public class AreaDto {

    @ApiModelProperty(required = true,value = "国家ID",example = "240")
    private Integer country_id;

    @ApiModelProperty(value = "中国区域ID,境外国家 无需传参")
    private Integer district_id;

    @ApiModelProperty(required = true,value = "省ID",example = "620000")
    private Integer province_id;

    @ApiModelProperty(required = true,value = "城市ID",example = "620400")
    private Integer city_id;

    @ApiModelProperty(required = true,value = "区县ID",example = "240")
    private Integer county_id;

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getCounty_id() {
        return county_id;
    }

    public void setCounty_id(Integer county_id) {
        this.county_id = county_id;
    }
}
