package com.kunlun.erp.core.dto.area;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName DistrictDto
 * @Description 区域DTO
 * @Author Jm.zhang
 * @Date 2019/11/25 15:18
 * @Version 1.0
 **/
@ApiModel(description = "中国大区数据")
public class DistrictDto {
    @ApiModelProperty(value = "大区ID")
    @JSONField(ordinal = 1)
    private Integer district_id;

    @ApiModelProperty(value = "所属国家ID")
    @JSONField(ordinal = 2)
    private Integer country_id;

    @ApiModelProperty(value = "大区名称")
    @JSONField(ordinal = 3)
    private String zh_name;

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

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }
}
