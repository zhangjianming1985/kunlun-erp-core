package com.kunlun.erp.core.dto.area;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CountryDto
 * @Description 中国DTO
 * @Author Jm.zhang
 * @Date 2019/11/25 12:33
 * @Version 1.0
 **/
@ApiModel(description = "国家数据")
public class CountryDto {
    @ApiModelProperty(value = "国家ID")
    @JSONField(ordinal = 1)
    private Integer country_id;

    @ApiModelProperty(value = "国家名称")
    @JSONField(ordinal = 2)
    private String zh_name;

    @ApiModelProperty(value = "国家首字母")
    @JSONField(ordinal = 3)
    private String first_letter;

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

}
