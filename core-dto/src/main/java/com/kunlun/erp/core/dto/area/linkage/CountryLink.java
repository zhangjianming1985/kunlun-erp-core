package com.kunlun.erp.core.dto.area.linkage;

import com.alibaba.fastjson.annotation.JSONField;
import com.kunlun.erp.core.dto.area.CityDto;
import com.kunlun.erp.core.dto.area.CountryDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName CountryLink
 * @Description 境外国家
 * @Author Jm.zhang
 * @Date 2019/11/25 17:00
 * @Version 1.0
 **/
@ApiModel(description = "国家联动")
public class CountryLink extends CountryDto {
    /**
     * 国家包含的城市数据
     */
    @JSONField(ordinal = 4)
    private List<CityDto> city_data;


    public List<CityDto> getCity_data() {
        return city_data;
    }

    public void setCity_data(List<CityDto> city_data) {
        this.city_data = city_data;
    }
}
