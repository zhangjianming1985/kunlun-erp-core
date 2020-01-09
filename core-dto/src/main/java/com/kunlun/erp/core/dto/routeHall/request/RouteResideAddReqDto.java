package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteResideDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteResideAddReqDto
 * @Description 设置住宿数据请求
 * @Author Jm.zhang
 * @Date 2019/12/20 16:54
 * @Version 1.0
 **/
@ApiModel(description = "设置住宿数据请求")
public class RouteResideAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    private List<RouteResideDto> reside_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteResideDto> getReside_data() {
        return reside_data;
    }

    public void setReside_data(List<RouteResideDto> reside_data) {
        this.reside_data = reside_data;
    }
}
