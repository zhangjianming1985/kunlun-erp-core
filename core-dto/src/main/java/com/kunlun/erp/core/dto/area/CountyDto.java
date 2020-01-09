package com.kunlun.erp.core.dto.area;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CountyDto
 * @Description 区县数据对象
 * @Author Jm.zhang
 * @Date 2019/11/25 15:14
 * @Version 1.0
 **/
@ApiModel(description = "区县数据")
public class CountyDto {
    @ApiModelProperty(value = "区县ID")
    @JSONField(ordinal = 1)
    private Integer county_id;

    @ApiModelProperty(value = "城市ID")
    @JSONField(ordinal = 2)
    private Integer city_id;


    @ApiModelProperty(value = "区县名称")
    @JSONField(ordinal = 3)
    private String zh_name;


    @ApiModelProperty(value = "区县首字母")
    @JSONField(ordinal = 4)
    private String first_letter;


    public Integer getCounty_id() {
        return county_id;
    }

    public void setCounty_id(Integer county_id) {
        this.county_id = county_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
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
