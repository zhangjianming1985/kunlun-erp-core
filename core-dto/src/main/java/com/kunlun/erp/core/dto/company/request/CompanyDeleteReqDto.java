package com.kunlun.erp.core.dto.company.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName CompanyDeleteReqDto
 * @Description 删除企业数据
 * @Author Jm.zhang
 * @Date 2019-12-26 0:59
 * @Version 1.0
 **/
@ApiModel(description = "删除企业数据")
public class CompanyDeleteReqDto {
    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    @NotNull(message = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID)
    private Integer company_type;

    @ApiModelProperty(required = true,value = "企业编号",example = "1000001885054422")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }
}
