package com.kunlun.erp.core.dto.routeHall.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteMotorcadeAddRespDto
 * @Description 设置车队数据结果响应
 * @Author Jm.zhang
 * @Date 2019-12-22 22:32
 * @Version 1.0
 **/
public class RouteMotorcadeAddRespDto {
    @ApiModelProperty(value = "团号",example = "66666666")
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
