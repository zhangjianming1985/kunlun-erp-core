package com.kunlun.erp.core.dto.product.request;

import com.kunlun.erp.core.dto.common.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName RouteProductListReqDto
 * @Description 线路产品检索请求
 * @Author Jm.zhang
 * @Date 2019/12/9 17:35
 * @Version 1.0
 **/
@ApiModel(description = "线路产品检索请求")
public class RouteProductListReqDto {
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(value = "产品代码",example = "5000554545550")
    private String product_code;

    @ApiModelProperty(value = "产品名称",example = "阿拉善欢乐线")
    private String product_name;

    @ApiModelProperty(value = "创建人",example = "创建人名字")
    private String creator_name;

    @ApiModelProperty(value = "产品类型",example = "阿拉善自驾游")
    private Integer product_category_code;

    @ApiModelProperty(value = "区域检索")
    private AreaDto area;

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

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

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
}
