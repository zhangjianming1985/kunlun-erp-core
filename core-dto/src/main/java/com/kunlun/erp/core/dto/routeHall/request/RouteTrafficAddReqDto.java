package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteTrafficDto;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteTrafficAddReqDto
 * @Description 设置交通票务数据请求
 * @Author Jm.zhang
 * @Date 2019-12-24 23:41
 * @Version 1.0
 **/
@ApiModel(description = "设置交通票务数据请求")
public class RouteTrafficAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    private List<RouteTrafficDto> traffic_data;


    private List<OrderClientDto> client_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteTrafficDto> getTraffic_data() {
        return traffic_data;
    }

    public void setTraffic_data(List<RouteTrafficDto> traffic_data) {
        this.traffic_data = traffic_data;
    }

    public List<OrderClientDto> getClient_data() {
        return client_data;
    }

    public void setClient_data(List<OrderClientDto> client_data) {
        this.client_data = client_data;
    }
}
