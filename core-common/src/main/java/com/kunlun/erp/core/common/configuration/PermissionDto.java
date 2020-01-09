package com.kunlun.erp.core.common.configuration;

/**
 * @ClassName PermissionDto
 * @Description 权限数据
 * @Author Jm.zhang
 * @Date 2019/11/22 17:28
 * @Version 1.0
 **/

public class PermissionDto {

    /**
     * 权限名称
     */
    private String perName;
    /**
     * 权限key
     */
    private String permissionKey;
    /**
     * 权限类型1、菜单，2、操作，按钮等控制
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 父权限key
     */
    private String parentPermissionKey;

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getParentPermissionKey() {
        return parentPermissionKey;
    }

    public void setParentPermissionKey(String parentPermissionKey) {
        this.parentPermissionKey = parentPermissionKey;
    }
}
