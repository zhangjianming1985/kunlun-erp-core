package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProductCategoryDelRespDto
 * @Description 产品类别删除响应
 * @Author Jm.zhang
 * @Date 2019/12/31 12:07
 * @Version 1.0
 **/
@ApiModel(description = "产品类别删除响应")
public class ProductCategoryDelRespDto {
    @ApiModelProperty(value = "产品类别编号",example = "666666666")
    private String category_code;


    @ApiModelProperty(value = "成功删除条数",example = "1")
    private int del_count;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public int getDel_count() {
        return del_count;
    }

    public void setDel_count(int del_count) {
        this.del_count = del_count;
    }
}
