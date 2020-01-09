package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteOtherDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteOtherAddReqDto
 * @Description 设置团的其他数据请求
 * @Author Jm.zhang
 * @Date 2019-12-23 0:53
 * @Version 1.0
 **/
@ApiModel(description = "设置团的其他数据请求")
public class RouteOtherAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    private List<RouteOtherDto> other_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteOtherDto> getOther_data() {
        return other_data;
    }

    public void setOther_data(List<RouteOtherDto> other_data) {
        this.other_data = other_data;
    }
}
