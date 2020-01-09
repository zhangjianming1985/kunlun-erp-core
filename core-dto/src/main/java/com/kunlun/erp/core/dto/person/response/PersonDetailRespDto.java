package com.kunlun.erp.core.dto.person.response;

import com.kunlun.erp.core.dto.person.PersonDto;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName PersonDetailRespDto
 * @Description 人员详情数据响应
 * @Author Jm.zhang
 * @Date 2019/12/5 17:18
 * @Version 1.0
 **/
@ApiModel(description = "人员详情信息")
public class PersonDetailRespDto {

    private PersonDto person_info;

    public PersonDto getPerson_info() {
        return person_info;
    }

    public void setPerson_info(PersonDto person_info) {
        this.person_info = person_info;
    }
}
