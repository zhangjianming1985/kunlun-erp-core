package com.kunlun.erp.core.dto.area;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProvinceDto
 * @Description 省份数据对象
 * @Author Jm.zhang
 * @Date 2019/11/25 15:07
 * @Version 1.0
 **/
@ApiModel(description = "省份数据")
public class ProvinceDto {
    @ApiModelProperty(value = "省份ID")
    @JSONField(ordinal = 1)
    private Integer province_id;

    @ApiModelProperty(value = "省份名称")
    @JSONField(ordinal = 2)
    private String zh_name;

    @ApiModelProperty(value = "省份首字母")
    @JSONField(ordinal = 3)
    private String first_letter;

    @ApiModelProperty(value = "属于大区ID")
    @JSONField(ordinal = 4)
    private Integer district_id;


    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
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

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

}
