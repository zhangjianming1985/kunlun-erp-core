package com.kunlun.erp.core.dto.company.response;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName CompanyListRespDto
 * @Description 公司信息响应信息
 * @Author Jm.zhang
 * @Date 2019/11/26 15:37
 * @Version 1.0
 **/
@ApiModel(description = "企业列表数据")
public class CompanyListRespDto {
    private PageInfo<CompanyListDto> page_data;


    public PageInfo<CompanyListDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<CompanyListDto> page_data) {
        this.page_data = page_data;
    }
}
