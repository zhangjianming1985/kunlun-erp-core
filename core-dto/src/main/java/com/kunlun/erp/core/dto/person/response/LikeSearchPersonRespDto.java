package com.kunlun.erp.core.dto.person.response;

import com.kunlun.erp.core.dto.person.LikeSearchPersonDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName LikeSearchPersonRespDto
 * @Description 模糊检索人员响应
 * @Author Jm.zhang
 * @Date 2019/12/20 11:33
 * @Version 1.0
 **/
@ApiModel(description = "模糊检索人员响应")
public class LikeSearchPersonRespDto {

    private List<LikeSearchPersonDto> person_data;

    public List<LikeSearchPersonDto> getPerson_data() {
        return person_data;
    }

    public void setPerson_data(List<LikeSearchPersonDto> person_data) {
        this.person_data = person_data;
    }
}
