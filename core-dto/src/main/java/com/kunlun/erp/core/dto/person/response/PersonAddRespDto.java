package com.kunlun.erp.core.dto.person.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName PersonAddRespDto
 * @Description 新增人员响应数据
 * @Author Jm.zhang
 * @Date 2019/12/3 15:25
 * @Version 1.0
 **/
@ApiModel(description = "创建人员响应结果")
public class PersonAddRespDto {

    @ApiModelProperty(value = "人员唯一编号")
    private String person_code;

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }
}
