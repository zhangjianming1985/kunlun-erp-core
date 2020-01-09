package com.kunlun.erp.core.dto.person.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName PersonUpdateRespDto
 * @Description 响应人员信息修改结果
 * @Author Jm.zhang
 * @Date 2019/12/7 15:37
 * @Version 1.0
 **/
@ApiModel(description = "修改人员信息结果响应")
public class PersonUpdateRespDto {
    @ApiModelProperty(value = "被更新的人员编号")
    private String person_code;

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }
}
