package com.kunlun.erp.core.dto.routeOrder.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName ClientAddReqDto
 * @Description 维护出游人数据
 * @Author Jm.zhang
 * @Date 2019/12/24 12:08
 * @Version 1.0
 **/
@ApiModel(description = "维护出游人数据")
public class ClientAddReqDto {
    @ApiModelProperty(required = true,value = "线路订单编号",example = "66666666666")
    @NotBlank(message = ErrorCodeConstant.ROUTE_ORDER_CODE_INVALID)
    private String order_code;

    /**
     * 出游人数据
     */
    private List<OrderClientDto> client_data;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public List<OrderClientDto> getClient_data() {
        return client_data;
    }

    public void setClient_data(List<OrderClientDto> client_data) {
        this.client_data = client_data;
    }
}
