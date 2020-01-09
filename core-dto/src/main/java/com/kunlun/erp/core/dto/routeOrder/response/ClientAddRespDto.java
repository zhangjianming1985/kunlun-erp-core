package com.kunlun.erp.core.dto.routeOrder.response;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ClientAddRespDto
 * @Description 维护出游人数据响应
 * @Author Jm.zhang
 * @Date 2019/12/24 12:58
 * @Version 1.0
 **/
public class ClientAddRespDto {
    @ApiModelProperty(required = true,value = "线路订单编号",example = "66666666666")
    @NotBlank(message = ErrorCodeConstant.ROUTE_ORDER_CODE_INVALID)
    private String order_code;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
}
