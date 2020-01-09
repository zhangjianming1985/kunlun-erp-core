package com.kunlun.erp.core.dto.area.linkage;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName InternalAreaLink
 * @Description 境内区域联动
 * @Author Jm.zhang
 * @Date 2019/11/25 17:40
 * @Version 1.0
 **/
@ApiModel(description = "境内数据")
public class InternalAreaLink {

    /**
     * 国内线路  大区数据
     */
    private List<DistrictLink> district_data;

    public List<DistrictLink> getDistrict_data() {
        return district_data;
    }

    public void setDistrict_data(List<DistrictLink> district_data) {
        this.district_data = district_data;
    }
}
