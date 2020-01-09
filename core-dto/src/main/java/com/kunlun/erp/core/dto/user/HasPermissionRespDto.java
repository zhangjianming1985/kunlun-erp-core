package com.kunlun.erp.core.dto.user;

/**
 * @ClassName HasPermissionRespDto
 * @Description 验权响应
 * @Author Jm.zhang
 * @Date 2019/11/23 12:15
 * @Version 1.0
 **/
public class HasPermissionRespDto {
    private Integer uid;
    private String userName;
    private String loginName;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
