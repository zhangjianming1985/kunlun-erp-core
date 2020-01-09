package com.kunlun.erp.core.dto.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName PersonDto
 * @Description 人员信息
 * @Author Jm.zhang
 * @Date 2019/11/18 15:46
 * @Version 1.0
 **/
@ApiModel(description = "人员信息数据")
public class PersonDto {
    @ApiModelProperty(required = true,value = "人员类型：0=普通联系人、1=导游",example = "0")
    private Integer person_type;
    @ApiModelProperty(value = "人员编号，创建时 无需传参",example = "1000001314782080")
    private String person_code;
    @ApiModelProperty(required = true,value = "姓名",example = "李四")
    private String person_name;

    @ApiModelProperty(value = "手机号码",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(value = "微信号",example = "leo_6688")
    private String person_wechat;

    @ApiModelProperty(value = "电话,座机",example = "0755-8564456")
    private String person_phone;

    @ApiModelProperty(value = "QQ号",example = "78098702")
    private String person_qq;

    @ApiModelProperty(value = "职务",example = "小小程序员")
    private String position;

    @ApiModelProperty(value = "备注",example = "这是一个备注")
    private String remarks;

    @ApiModelProperty(value = "创建人名字",example = "李宗盛")
    private String creator_name;




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

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }

    public String getPerson_wechat() {
        return person_wechat;
    }

    public void setPerson_wechat(String person_wechat) {
        this.person_wechat = person_wechat;
    }

    public String getPerson_phone() {
        return person_phone;
    }

    public void setPerson_phone(String person_phone) {
        this.person_phone = person_phone;
    }

    public String getPerson_qq() {
        return person_qq;
    }

    public void setPerson_qq(String person_qq) {
        this.person_qq = person_qq;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }
}
