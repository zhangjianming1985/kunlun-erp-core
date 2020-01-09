package com.kunlun.erp.core.dto.user;

/**
 * @ClassName HasPermissionReqDto
 * @Description 验证权请求
 * @Author Jm.zhang
 * @Date 2019/11/23 12:14
 * @Version 1.0
 **/
public class HasPermissionReqDto {

    private String permissionKey;

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }
}
