package com.kunlun.erp.core.dto.area.linkage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName AreaLink
 * @Description 区域联动总数据
 * @Author Jm.zhang
 * @Date 2019-11-25 21:14
 * @Version 1.0
 **/
@ApiModel(description = "所有地区级联数据")
public class AreaLink {

    private ForeignAreaLink foreign_data;
    @ApiModelProperty(value = "境内数据")
    private InternalAreaLink internal_data;


    public ForeignAreaLink getForeign_data() {
        return foreign_data;
    }

    public void setForeign_data(ForeignAreaLink foreign_data) {
        this.foreign_data = foreign_data;
    }

    public InternalAreaLink getInternal_data() {
        return internal_data;
    }

    public void setInternal_data(InternalAreaLink internal_data) {
        this.internal_data = internal_data;
    }
}
