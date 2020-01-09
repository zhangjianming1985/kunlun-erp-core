package com.kunlun.erp.core.dto.routeOrder.response;

import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName ClientListRespDto
 * @Description 出游人列表响应
 * @Author Jm.zhang
 * @Date 2019/12/24 14:24
 * @Version 1.0
 **/
@ApiModel(description = "出游人列表响应")
public class ClientListRespDto {

    private List<OrderClientDto> client_data;

    public List<OrderClientDto> getClient_data() {
        return client_data;
    }

    public void setClient_data(List<OrderClientDto> client_data) {
        this.client_data = client_data;
    }
}
