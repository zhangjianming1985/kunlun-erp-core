package com.kunlun.erp.core.dto.person.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LikeSearchPersonReqDto
 * @Description 模糊检索人员信息
 * @Author Jm.zhang
 * @Date 2019/12/20 11:29
 * @Version 1.0
 **/
@ApiModel(description = "模糊检索人员信息")
public class LikeSearchPersonReqDto {
    @ApiModelProperty(required = true,value = "企业编号",example = "1000500")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(required = true,value = "人员类型：0=普通联系人、1=导游",example = "1")
    private Integer person_type;

    @ApiModelProperty(value = "姓名， 可用于模糊检索",example = "刘德")
    private String person_name;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Integer getPerson_type() {
        return person_type;
    }

    public void setPerson_type(Integer person_type) {
        this.person_type = person_type;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
}
