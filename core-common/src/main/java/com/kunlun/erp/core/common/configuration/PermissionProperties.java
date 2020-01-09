package com.kunlun.erp.core.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @ClassName PermissionProperties
 * @Description 权限资源配置
 * @Author Jm.zhang
 * @Date 2019-11-22 23:28
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "props")
@PropertySource(value = "permission.properties",encoding = "UTF-8")
public class PermissionProperties {
    /**
     * 权限资源集合
     */
    private List<PermissionDto> data;

    public List<PermissionDto> getData() {
        return data;
    }

    public void setData(List<PermissionDto> data) {
        this.data = data;
    }
}
