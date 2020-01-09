package com.kunlun.erp.core.dto.routeHall;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName SalesChannelDto
 * @Description 销售渠道数据
 * @Author Jm.zhang
 * @Date 2019/12/24 19:10
 * @Version 1.0
 **/
@ApiModel(description = "销售渠道数据")
public class SalesChannelDto {

    @ApiModelProperty(value = "销售渠道名称",example = "兴旺旅游公司")
    private String company_name;

    @ApiModelProperty(value = "联系人名字",example = "小李杜")
    private String contact_name;

    @ApiModelProperty(value = "联系人手机号码",example = "15915328866")
    private String contact_mobile;

    @ApiModelProperty(value = "出游人数量",example = "10")
    private int client_count;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public int getClient_count() {
        return client_count;
    }

    public void setClient_count(int client_count) {
        this.client_count = client_count;
    }
}
