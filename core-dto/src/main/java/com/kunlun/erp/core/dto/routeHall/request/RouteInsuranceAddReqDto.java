package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteInsuranceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteInsuranceAddReqDto
 * @Description 设置保险数据请求
 * @Author Jm.zhang
 * @Date 2019-12-23 0:00
 * @Version 1.0
 **/
@ApiModel(description = "设置保险数据请求")
public class RouteInsuranceAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;
    @Valid
    private List<RouteInsuranceDto> insurance_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteInsuranceDto> getInsurance_data() {
        return insurance_data;
    }

    public void setInsurance_data(List<RouteInsuranceDto> insurance_data) {
        this.insurance_data = insurance_data;
    }
}
