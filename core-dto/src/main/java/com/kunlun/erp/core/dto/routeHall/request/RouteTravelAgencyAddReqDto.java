package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteTravelAgencyAddReqDto
 * @Description 设置地接供应商数据请求
 * @Author Jm.zhang
 * @Date 2019/12/25 12:08
 * @Version 1.0
 **/
@ApiModel(description = "设置地接供应商数据请求")
public class RouteTravelAgencyAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;
    @Valid
    private List<RouteTravelAgencyDto> travel_agency_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteTravelAgencyDto> getTravel_agency_data() {
        return travel_agency_data;
    }

    public void setTravel_agency_data(List<RouteTravelAgencyDto> travel_agency_data) {
        this.travel_agency_data = travel_agency_data;
    }
}
