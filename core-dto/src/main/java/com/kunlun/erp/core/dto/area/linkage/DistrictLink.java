package com.kunlun.erp.core.dto.area.linkage;

import com.alibaba.fastjson.annotation.JSONField;
import com.kunlun.erp.core.dto.area.DistrictDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName DistrictLink
 * @Description 中国区域划分
 * @Author Jm.zhang
 * @Date 2019/11/25 16:55
 * @Version 1.0
 **/
@ApiModel(description = "大区数据联动")
public class DistrictLink extends DistrictDto {
    /**
     * 大区数据包含的 省份数据
     */
    @JSONField(ordinal = 4)
    private List<ProvinceLink> province_data;


    public List<ProvinceLink> getProvince_data() {
        return province_data;
    }

    public void setProvince_data(List<ProvinceLink> province_data) {
        this.province_data = province_data;
    }
}
