package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteMealDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteMealAddReqDto
 * @Description 设置用餐数据请求
 * @Author Jm.zhang
 * @Date 2019-12-22 18:41
 * @Version 1.0
 **/
@ApiModel(description = "设置景点门票数据请求")
public class RouteMealAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;
    @Valid
    private List<RouteMealDto> meal_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteMealDto> getMeal_data() {
        return meal_data;
    }

    public void setMeal_data(List<RouteMealDto> meal_data) {
        this.meal_data = meal_data;
    }
}
