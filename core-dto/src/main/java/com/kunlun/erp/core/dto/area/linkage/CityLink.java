package com.kunlun.erp.core.dto.area.linkage;

import com.alibaba.fastjson.annotation.JSONField;
import com.kunlun.erp.core.dto.area.CityDto;
import com.kunlun.erp.core.dto.area.CountyDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName CityLink
 * @Description 联动的城市
 * @Author Jm.zhang
 * @Date 2019-11-25 21:11
 * @Version 1.0
 **/
@ApiModel(description = "城市联动")
public class CityLink extends CityDto {
    /**
     * 联动的区县
     */
    @JSONField(ordinal = 6)
    private List<CountyDto> county_data;

    public List<CountyDto> getCounty_data() {
        return county_data;
    }

    public void setCounty_data(List<CountyDto> county_data) {
        this.county_data = county_data;
    }
}
