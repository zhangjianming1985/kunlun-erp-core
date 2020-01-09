package com.kunlun.erp.core.dto.routeHall.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteOtherAddRespDto
 * @Description 设置其他数据结果响应
 * @Author Jm.zhang
 * @Date 2019-12-23 0:56
 * @Version 1.0
 **/
@ApiModel(description = "设置其他数据结果响应")
public class RouteOtherAddRespDto {
    @ApiModelProperty(value = "团号",example = "66666666")
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
