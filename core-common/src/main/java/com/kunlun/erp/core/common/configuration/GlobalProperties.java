package com.kunlun.erp.core.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName GlobalProperties
 * @Description properties配置
 * @Author Jm.zhang
 * @Date 2019-11-23 0:46
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "config")
@PropertySource(value = "config.properties",encoding = "UTF-8")
public class GlobalProperties {
    /**
     * 用户信息API
     */
    private String account_api_url;
    /**
     * 添加权限资源API
     */
    private String add_permission_api_url;
    /**
     * 权限校验API
     */
    private String has_permission_api_url;

    public String getAccount_api_url() {
        return account_api_url;
    }

    public void setAccount_api_url(String account_api_url) {
        this.account_api_url = account_api_url;
    }

    public String getAdd_permission_api_url() {
        return add_permission_api_url;
    }

    public void setAdd_permission_api_url(String add_permission_api_url) {
        this.add_permission_api_url = add_permission_api_url;
    }

    public String getHas_permission_api_url() {
        return has_permission_api_url;
    }

    public void setHas_permission_api_url(String has_permission_api_url) {
        this.has_permission_api_url = has_permission_api_url;
    }
}
