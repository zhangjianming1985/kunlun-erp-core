package com.kunlun.erp.core.dto.company.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName LikeNameDto
 * @Description 模糊检索响应数据
 * @Author Jm.zhang
 * @Date 2019/12/16 10:45
 * @Version 1.0
 **/
@ApiModel(description = "模糊检索响应数据")
public class LikeNameDto {
    @ApiModelProperty(value = "企业编号",example = "1000000995802403")
    private String company_code;
    @ApiModelProperty(value = "企业名称",example = "测试线渠道2")
    private String company_name;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
