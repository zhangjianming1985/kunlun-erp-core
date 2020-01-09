package com.kunlun.erp.core.dto.product.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProductCategoryListReqDto
 * @Description 获取产品类别列表
 * @Author Jm.zhang
 * @Date 2019/12/6 9:43
 * @Version 1.0
 **/
@ApiModel(description = "获取产品类别列表")
public class ProductCategoryListReqDto {
    @ApiModelProperty(value = "当前页码，默认1",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数，默认20",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(value = "检索类别名称",example = "线路")
    private String category_name;

    @ApiModelProperty(value = "产品类别状态，0=启用 1=禁用")
    private Integer category_state;

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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_state() {
        return category_state;
    }

    public void setCategory_state(Integer category_state) {
        this.category_state = category_state;
    }
}
