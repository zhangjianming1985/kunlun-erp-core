package com.kunlun.erp.core.dto.routeHall.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName HallProductListReqDto
 * @Description 报名大厅产品列表
 * @Author Jm.zhang
 * @Date 2019/12/18 21:20
 * @Version 1.0
 **/
@ApiModel(description = "获取报名大厅产品列表")
public class HallProductListReqDto {
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(value = "线路ID",example = "52255255")
    private String product_code;

    @ApiModelProperty(value = "团号",example = "2565874")
    private String group_code;

    @ApiModelProperty(value = "线路名称",example = "测试线路")
    private String product_name;

    @ApiModelProperty(value = "状态 0=待出团、1=行程中、2=已结束、3=已取消,4=已删除",example = "0")
    private Integer status;

    @ApiModelProperty(value = "内部团号",example = "555555555")
    private String internal_code;

    @ApiModelProperty(value = "发团开始日期",example = "2019-10-01")
    private String start_date;

    @ApiModelProperty(value = "发团结束日期",example = "2019-10-20")
    private String end_date;

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInternal_code() {
        return internal_code;
    }

    public void setInternal_code(String internal_code) {
        this.internal_code = internal_code;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
