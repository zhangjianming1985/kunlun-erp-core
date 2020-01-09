package com.kunlun.erp.core.dto.person.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.person.PersonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName PersonAddReqDto
 * @Description 创建人员数据
 * @Author Jm.zhang
 * @Date 2019/12/3 14:24
 * @Version 1.0
 **/
@ApiModel(description = "人员数据")
public class PersonAddReqDto extends PersonDto {
    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    @NotNull(message = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID)
    private Integer company_type;

    @ApiModelProperty(required = true,value = "企业编号",example = "1000000123963044")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }
}
