package com.kunlun.erp.core.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName LeaderDto
 * @Description 负责人信息
 * @Author Jm.zhang
 * @Date 2019/11/18 14:43
 * @Version 1.0
 **/
@ApiModel(description = " 负责人信息")
public class LeaderDto {
    @ApiModelProperty(value = "负责人姓名 选填，姓名，支持中英文及少数民族姓名•或.特殊标点，最多32个字符",example = "测试负责人姓名")
    private String leader_name;

    @ApiModelProperty(value = "负责人手机 手机号需校验",example = "15915328866")
    private String leader_mobile;

    @ApiModelProperty(value = "负责人微信号，16位字符，支持字母大小写、数字及下划线；",example = "leo_6688")
    private String leader_wechat;

    @ApiModelProperty(value = "负责人地址  最多80个字符",example = "测试负责人地址测试负责人地址测试负责人地址测试负责人地址测试负责人地址测试负责人地址")
    private String leader_address;


    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getLeader_mobile() {
        return leader_mobile;
    }

    public void setLeader_mobile(String leader_mobile) {
        this.leader_mobile = leader_mobile;
    }

    public String getLeader_wechat() {
        return leader_wechat;
    }

    public void setLeader_wechat(String leader_wechat) {
        this.leader_wechat = leader_wechat;
    }

    public String getLeader_address() {
        return leader_address;
    }

    public void setLeader_address(String leader_address) {
        this.leader_address = leader_address;
    }
}
