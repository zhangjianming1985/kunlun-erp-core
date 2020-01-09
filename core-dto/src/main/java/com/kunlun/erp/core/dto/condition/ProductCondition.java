package com.kunlun.erp.core.dto.condition;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProductCondition
 * @Description 产品检索条件
 * @Author Jm.zhang
 * @Date 2019/12/9 18:08
 * @Version 1.0
 **/
public class ProductCondition extends BaseCondition{

    @ApiModelProperty(value = "产品代码",example = "5000554545550")
    private String product_code;

    @ApiModelProperty(value = "产品名称",example = "阿拉善欢乐线")
    private String product_name;

    @ApiModelProperty(value = "创建人",example = "创建人名字")
    private String creator_name;

    @ApiModelProperty(value = "产品类型",example = "阿拉善自驾游")
    private Integer product_category_code;


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

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public Integer getProduct_category_code() {
        return product_category_code;
    }

    public void setProduct_category_code(Integer product_category_code) {
        this.product_category_code = product_category_code;
    }
}
