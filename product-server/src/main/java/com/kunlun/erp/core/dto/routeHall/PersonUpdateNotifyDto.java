package com.kunlun.erp.core.dto.routeHall;

import java.util.Date;

/**
 * @ClassName PersonUpdateNotifyDto
 * @Description 人员变动引起的修改
 * @Author Jm.zhang
 * @Date 2019/12/23 17:48
 * @Version 1.0
 **/
public class PersonUpdateNotifyDto {
    /**
     * 团号
     */
    private String group_code;

    private Date update_time;

    /**
     * 确认人数变动
     */
    private Integer confirm_count;

    /**
     * 站位人数变动
     */
    private Integer hold_count;

    /**
     * 余位人数变动
     */
    private Integer remain_count;


    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getConfirm_count() {
        return confirm_count;
    }

    public void setConfirm_count(Integer confirm_count) {
        this.confirm_count = confirm_count;
    }

    public Integer getHold_count() {
        return hold_count;
    }

    public void setHold_count(Integer hold_count) {
        this.hold_count = hold_count;
    }

    public Integer getRemain_count() {
        return remain_count;
    }

    public void setRemain_count(Integer remain_count) {
        this.remain_count = remain_count;
    }
}
