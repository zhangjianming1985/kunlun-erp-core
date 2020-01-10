package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteMotorcadeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteMotorcadeAddReqDto
 * @Description 设置车队数据请求
 * @Author Jm.zhang
 * @Date 2019-12-22 22:28
 * @Version 1.0
 **/
@ApiModel(description = "设置车队数据请求")
public class RouteMotorcadeAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;
    @Valid
    private List<RouteMotorcadeDto> motorcade_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteMotorcadeDto> getMotorcade_data() {
        return motorcade_data;
    }

    public void setMotorcade_data(List<RouteMotorcadeDto> motorcade_data) {
        this.motorcade_data = motorcade_data;
    }
}
