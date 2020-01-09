package com.kunlun.erp.core.dto.person.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName PersonDetailReqDto
 * @Description 人员详情请求
 * @Author Jm.zhang
 * @Date 2019/12/5 17:14
 * @Version 1.0
 **/
@ApiModel(description = "请求人员详情参数")
public class PersonDetailReqDto {

    @ApiModelProperty(required = true,value = "人员编号",example = "3000000727872433")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String person_code;

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }
}
