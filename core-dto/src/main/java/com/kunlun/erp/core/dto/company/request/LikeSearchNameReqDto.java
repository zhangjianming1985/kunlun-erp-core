package com.kunlun.erp.core.dto.company.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName LikeSearchNameReqDto
 * @Description 模糊检索企业名称
 * @Author Jm.zhang
 * @Date 2019/12/16 10:10
 * @Version 1.0
 **/
@ApiModel(description = "模糊检索企业名称")
public class LikeSearchNameReqDto {
    @ApiModelProperty(value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商、20=所有销售渠道、21=所有供应商",example = "0")
    private Integer company_type;

    @ApiModelProperty(value = "企业名称",example = "旅行社")
    private String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }
}
