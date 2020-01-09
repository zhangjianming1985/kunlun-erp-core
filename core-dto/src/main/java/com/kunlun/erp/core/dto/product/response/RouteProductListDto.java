package com.kunlun.erp.core.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteProductListDto
 * @Description 线路产品数据
 * @Author Jm.zhang
 * @Date 2019/12/9 17:42
 * @Version 1.0
 **/
@ApiModel(description = "线路产品列表数据")
public class RouteProductListDto {
    @ApiModelProperty(value = "产品代码",example = "55555555")
    private String product_code;

    @ApiModelProperty(value = "产品名称",example = "阿拉善游击队")
    private String product_name;

    @ApiModelProperty(value = "产品分类",example = "野营部队")
    private String product_category_name;

    @ApiModelProperty(value = "区域",example = "中国/华南/广东/深圳/龙岗")
    private String area_text;

    @ApiModelProperty(value = "创建人",example = "老王")
    private String creator_name;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_category_name() {
        return product_category_name;
    }

    public void setProduct_category_name(String product_category_name) {
        this.product_category_name = product_category_name;
    }

    public String getArea_text() {
        return area_text;
    }

    public void setArea_text(String area_text) {
        this.area_text = area_text;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }
}
