package com.kunlun.erp.core.dto.area.linkage;

import com.alibaba.fastjson.annotation.JSONField;
import com.kunlun.erp.core.dto.area.ProvinceDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName ProvinceLink
 * @Description 联动的省数据
 * @Author Jm.zhang
 * @Date 2019-11-25 21:22
 * @Version 1.0
 **/
@ApiModel(description = "省份联动")
public class ProvinceLink extends ProvinceDto {
    /**
     * 省份包含的城市
     */
    @JSONField(ordinal = 5)
    private List<CityLink> city_data;

    public List<CityLink> getCity_data() {
        return city_data;
    }

    public void setCity_data(List<CityLink> city_data) {
        this.city_data = city_data;
    }
}
