package com.kunlun.erp.core.dto.routeHall.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteTicketAddRespDto
 * @Description 设置景点门票数据结果响应
 * @Author Jm.zhang
 * @Date 2019/12/20 18:12
 * @Version 1.0
 **/
public class RouteTicketAddRespDto {
    @ApiModelProperty(value = "团号",example = "66666666")
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
