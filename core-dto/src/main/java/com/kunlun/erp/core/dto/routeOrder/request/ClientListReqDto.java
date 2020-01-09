package com.kunlun.erp.core.dto.routeOrder.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ClientListReqDto
 * @Description 订单出游人列表数据
 * @Author Jm.zhang
 * @Date 2019/12/24 14:18
 * @Version 1.0
 **/
@ApiModel(description = "获取订单出游人列表数据")
public class ClientListReqDto {
    @ApiModelProperty(required = true,value = "线路订单编号",example = "88888888888")
    @NotBlank(message = ErrorCodeConstant.ROUTE_ORDER_CODE_INVALID)
    private String order_code;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
}
