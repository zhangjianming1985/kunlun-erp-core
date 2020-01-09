package com.kunlun.erp.core.dto.routeHall.response;

import com.kunlun.erp.core.dto.routeHall.RouteTicketDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName RouteTicketListRespDto
 * @Description 线路团景点门票数据响应
 * @Author Jm.zhang
 * @Date 2019/12/20 18:08
 * @Version 1.0
 **/
@ApiModel(description = "线路团景点门票数据响应")
public class RouteTicketListRespDto {

    private List<RouteTicketDto> ticket_data;

    public List<RouteTicketDto> getTicket_data() {
        return ticket_data;
    }

    public void setTicket_data(List<RouteTicketDto> ticket_data) {
        this.ticket_data = ticket_data;
    }
}
