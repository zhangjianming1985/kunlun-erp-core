package com.kunlun.erp.core.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName FileProperties
 * @Description 文件相关配置
 * @Author Jm.zhang
 * @Date 2019/12/6 17:26
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "key")
@PropertySource(value = "file.properties",encoding = "UTF-8")
public class FileProperties {
    /**
     * 域名
     */
    private String sys_domain;
    /***
     * 图片存放目录
     */
    private String pic_folder_path;

    /**
     * 图片访问路径
     */
    private String pic_access_url;

    /**
     * 文档存放目录
     */
    private String doc_folder_path;

    /**
     * 文档访问路径
     */
    private String doc_access_url;

    public String getPic_folder_path() {
        return pic_folder_path;
    }

    public void setPic_folder_path(String pic_folder_path) {
        this.pic_folder_path = pic_folder_path;
    }

    public String getDoc_folder_path() {
        return doc_folder_path;
    }

    public void setDoc_folder_path(String doc_folder_path) {
        this.doc_folder_path = doc_folder_path;
    }

    public String getPic_access_url() {
        return pic_access_url;
    }

    public void setPic_access_url(String pic_access_url) {
        this.pic_access_url = pic_access_url;
    }

    public String getDoc_access_url() {
        return doc_access_url;
    }

    public void setDoc_access_url(String doc_access_url) {
        this.doc_access_url = doc_access_url;
    }

    public String getSys_domain() {
        return sys_domain;
    }

    public void setSys_domain(String sys_domain) {
        this.sys_domain = sys_domain;
    }
}
