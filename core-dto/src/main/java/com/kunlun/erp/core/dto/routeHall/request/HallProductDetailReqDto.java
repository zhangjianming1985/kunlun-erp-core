package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName HallProductDetailReqDto
 * @Description 获取报名大厅产品基础信息
 * @Author Jm.zhang
 * @Date 2019/12/19 14:25
 * @Version 1.0
 **/
@ApiModel(description = "获取报名大厅产品基础信息")
public class HallProductDetailReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
