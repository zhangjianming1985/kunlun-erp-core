package com.kunlun.erp.core.dto.area.linkage;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName ForeignAreaLink
 * @Description 境外区域联动 国家--城市
 * @Author Jm.zhang
 * @Date 2019/11/25 16:53
 * @Version 1.0
 **/

@ApiModel(description = "境外数据")
public class ForeignAreaLink {
    /**
     * 境外国家数据
     */
    private List<CountryLink> country_data;


    public List<CountryLink> getCountry_data() {
        return country_data;
    }

    public void setCountry_data(List<CountryLink> country_data) {
        this.country_data = country_data;
    }
}
