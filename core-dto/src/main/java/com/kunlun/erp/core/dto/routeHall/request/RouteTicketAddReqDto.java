package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeHall.RouteTicketDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteTicketAddReqDto
 * @Description 设置景点门票数据请求
 * @Author Jm.zhang
 * @Date 2019/12/20 18:10
 * @Version 1.0
 **/
@ApiModel(description = "设置景点门票数据请求")
public class RouteTicketAddReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    private List<RouteTicketDto> ticket_data;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<RouteTicketDto> getTicket_data() {
        return ticket_data;
    }

    public void setTicket_data(List<RouteTicketDto> ticket_data) {
        this.ticket_data = ticket_data;
    }
}
