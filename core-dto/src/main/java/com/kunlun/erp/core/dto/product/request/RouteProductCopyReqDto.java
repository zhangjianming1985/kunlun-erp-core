package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName RouteProductCopyReqDto
 * @Description 线路产品复制请求
 * @Author Jm.zhang
 * @Date 2019-12-31 1:26
 * @Version 1.0
 **/
@ApiModel(description = "线路产品复制请求")
public class RouteProductCopyReqDto {
    @ApiModelProperty(value = "产品编号集合,支持批量复制")
    @NotEmpty(message = ErrorCodeConstant.PRODUCT_IS_NOT_EXIST)
    private List<String> product_code;

    public List<String> getProduct_code() {
        return product_code;
    }

    public void setProduct_code(List<String> product_code) {
        this.product_code = product_code;
    }
}
