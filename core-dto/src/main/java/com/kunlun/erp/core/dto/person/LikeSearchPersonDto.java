package com.kunlun.erp.core.dto.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName LikeSearchPersonDto
 * @Description 模糊检索数据响应，用于select联动
 * @Author Jm.zhang
 * @Date 2019/12/20 11:35
 * @Version 1.0
 **/
@ApiModel(description = "模糊检索数据响应，用于select联动")
public class LikeSearchPersonDto {

    @ApiModelProperty(value = "人员编号",example = "1000001314782080")
    private String person_code;

    @ApiModelProperty(value = "姓名",example = "李四")
    private String person_name;

    @ApiModelProperty(value = "手机号码",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(value = "固话",example = "0755-25672036")
    private String person_phone;

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }

    public String getPerson_phone() {
        return person_phone;
    }

    public void setPerson_phone(String person_phone) {
        this.person_phone = person_phone;
    }
}
