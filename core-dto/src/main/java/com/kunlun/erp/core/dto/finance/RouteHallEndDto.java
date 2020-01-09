package com.kunlun.erp.core.dto.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteHallEndDto
 * @Description 行程结束的团数据
 * @Author Jm.zhang
 * @Date 2019/12/26 19:13
 * @Version 1.0
 **/
@ApiModel(description = "发团审核数据")
public class RouteHallEndDto {
    @ApiModelProperty(value = "团号",example ="13000001546253086" )
    private String group_code;

    @ApiModelProperty(value = "发团日期",example ="2019-12-12" )
    private String departure_date;

    @ApiModelProperty(value = "散团日期",example ="2019-12-20" )
    private String disband_date;

    @ApiModelProperty(value = "成人数量",example ="20" )
    private Integer adult_count;

    @ApiModelProperty(value = "儿童数量",example ="10" )
    private Integer children_count;

    @ApiModelProperty(value = "线路创建人",example ="小王" )
    private String creator_name;

    @ApiModelProperty(value = "审核状态,0=未审核、1=通过、2=驳回",example ="0" )
    private Integer approve_state;

    @ApiModelProperty(value = "操作人",example ="小曼" )
    private String approve_name;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDisband_date() {
        return disband_date;
    }

    public void setDisband_date(String disband_date) {
        this.disband_date = disband_date;
    }

    public Integer getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(Integer adult_count) {
        this.adult_count = adult_count;
    }

    public Integer getChildren_count() {
        return children_count;
    }

    public void setChildren_count(Integer children_count) {
        this.children_count = children_count;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public Integer getApprove_state() {
        return approve_state;
    }

    public void setApprove_state(Integer approve_state) {
        this.approve_state = approve_state;
    }

    public String getApprove_name() {
        return approve_name;
    }

    public void setApprove_name(String approve_name) {
        this.approve_name = approve_name;
    }
}
