package com.kunlun.erp.core.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName UploadResultDto
 * @Description 上传响应
 * @Author Jm.zhang
 * @Date 2019/12/7 9:27
 * @Version 1.0
 **/
@ApiModel(description = "文件上传结果")
public class UploadResultDto {
    @ApiModelProperty(value = "原始文件名称",example = "baidu.jpg")
    private String origin_name;

    @ApiModelProperty(value = "文件名称",example = "201911025698421.jpg")
    private String name;

    @ApiModelProperty(value = "状态",example = "done")
    private String status;

    @ApiModelProperty(value = "访问URL",example = "http://domain.com/pic/201911025698421.jpg")
    private String url;

    @ApiModelProperty(value = "访问URL",example = "http://domain.com/pic/201911025698421.jpg")
    private String thumbUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }
}
