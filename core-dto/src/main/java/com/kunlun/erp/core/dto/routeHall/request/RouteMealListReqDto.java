package com.kunlun.erp.core.dto.routeHall.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteMealListReqDto
 * @Description 获取线路团的用餐数据
 * @Author Jm.zhang
 * @Date 2019-12-22 18:38
 * @Version 1.0
 **/
@ApiModel(description ="获取线路团的用餐数据" )
public class RouteMealListReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
