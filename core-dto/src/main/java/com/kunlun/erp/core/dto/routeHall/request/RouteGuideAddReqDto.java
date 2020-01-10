package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteGuidesDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteGuideAddReqDto
 * @Description 设置导服数据
 * @Author Jm.zhang
 * @Date 2019-12-20 0:28
 * @Version 1.0
 **/
@ApiModel(description = "设置导服数据请求")
public class RouteGuideAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    @Valid
    private List<RouteGuidesDto> guide_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteGuidesDto> getGuide_data() {
        return guide_data;
    }

    public void setGuide_data(List<RouteGuidesDto> guide_data) {
        this.guide_data = guide_data;
    }
}
