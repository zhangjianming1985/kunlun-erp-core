package com.kunlun.erp.core.dto.finance.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.finance.CollectedDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName CollectedListRespDto
 * @Description 应收款请求响应
 * @Author Jm.zhang
 * @Date 2019/12/27 13:35
 * @Version 1.0
 **/
@ApiModel(description = "应收款请求响应")
public class CollectedListRespDto {

    private PageInfo<CollectedDto> page_data;

    public PageInfo<CollectedDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<CollectedDto> page_data) {
        this.page_data = page_data;
    }
}
