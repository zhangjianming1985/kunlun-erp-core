package com.kunlun.erp.core.dto.company.response;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName LikeSearchNameRespDto
 * @Description 企业模糊检索响应
 * @Author Jm.zhang
 * @Date 2019/12/16 10:47
 * @Version 1.0
 **/
@ApiModel(description = "企业模糊检索响应")
public class LikeSearchNameRespDto {

    private List<LikeNameDto> company_data;

    public List<LikeNameDto> getCompany_data() {
        return company_data;
    }

    public void setCompany_data(List<LikeNameDto> company_data) {
        this.company_data = company_data;
    }
}
