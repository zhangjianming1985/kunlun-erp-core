package com.kunlun.erp.core.dto.area.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName AreaReqDto
 * @Description 请求国家列表
 * @Author Jm.zhang
 * @Date 2019/12/5 9:36
 * @Version 1.0
 **/
@ApiModel(description = "请求参数")
public class AreaReqDto {
    @ApiModelProperty(value = "地区类型，0=获取境外国家数据、1=获取中国区域数据、2=获取所有的省份数据,3=所有数据")
    private Integer area_type;

    @ApiModelProperty(value = "中国区域ID, 获取区域的省份数据")
    private Integer district_id;

    @ApiModelProperty(value = "境外国家ID, 获取国家的城市数据")
    private Integer country_id;

    @ApiModelProperty(value = "省份ID, 获取省份的城市数据")
    private Integer province_id;

    @ApiModelProperty(value = "城市ID, 获取城市的区县数据")
    private Integer city_id;


    public Integer getArea_type() {
        return area_type;
    }

    public void setArea_type(Integer area_type) {
        this.area_type = area_type;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
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
}
