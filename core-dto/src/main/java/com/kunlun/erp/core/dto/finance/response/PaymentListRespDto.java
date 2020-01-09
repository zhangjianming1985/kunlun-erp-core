package com.kunlun.erp.core.dto.finance.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.finance.PaymentDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName PaymentListRespDto
 * @Description 供应商应付应收款响应
 * @Author Jm.zhang
 * @Date 2019-12-29 22:55
 * @Version 1.0
 **/
@ApiModel(description = "供应商应付应收款响应")
public class PaymentListRespDto {

    private PageInfo<PaymentDto> page_data;

    public PageInfo<PaymentDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<PaymentDto> page_data) {
        this.page_data = page_data;
    }
}
