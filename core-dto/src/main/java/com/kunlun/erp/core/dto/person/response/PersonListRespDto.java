package com.kunlun.erp.core.dto.person.response;

import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.dto.person.PersonDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName PersonListRespDto
 * @Description 人员数据响应
 * @Author Jm.zhang
 * @Date 2019-12-02 22:24
 * @Version 1.0
 **/
@ApiModel(description = "人员列表数据")
public class PersonListRespDto {
    private PageInfo<PersonDto> page_data;

    public PageInfo<PersonDto> getPage_data() {
        return page_data;
    }

    public void setPage_data(PageInfo<PersonDto> page_data) {
        this.page_data = page_data;
    }
}
