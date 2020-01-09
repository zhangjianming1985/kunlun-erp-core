package com.kunlun.erp.core.dto.area;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CityDto
 * @Description 城市数据对象
 * @Author Jm.zhang
 * @Date 2019/11/25 15:06
 * @Version 1.0
 **/
@ApiModel(description = "城市数据")
public class CityDto {
    @ApiModelProperty()
    @JSONField(ordinal = 1)
    private Integer city_id;

    @ApiModelProperty(value = "所属国家ID")
    @JSONField(ordinal = 2)
    private Integer country_id;

    @ApiModelProperty(value = "所属省份ID")
    @JSONField(ordinal = 3)
    private Integer province_id;

    @ApiModelProperty(value = "城市首字母")
    @JSONField(ordinal = 4)
    private String first_letter;

    @ApiModelProperty(value = "城市名称")
    @JSONField(ordinal = 5)
    private String zh_name;



    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
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

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }

}
