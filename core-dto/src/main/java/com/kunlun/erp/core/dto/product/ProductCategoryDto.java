package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ProductCategoryDto
 * @Description 产品类别数据
 * @Author Jm.zhang
 * @Date 2019/12/6 9:45
 * @Version 1.0
 **/
@ApiModel(description = "产品类别数据")
public class ProductCategoryDto {
    @ApiModelProperty(value = "产品类别编号")
    private String category_code;

    @ApiModelProperty(value = "产品类别名称")
    private String category_name;

    @ApiModelProperty(value = "产品类别状态，0=启用 1=禁用")
    private Integer category_state;

    @ApiModelProperty(value = "产品类别状态，启用 禁用")
    private String category_state_str;

    @ApiModelProperty(value = "创建人名字")
    private String creator_name;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
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
        this.setCategory_state_str(SysConstant.ProductCategoryState.getProductCategoryState(this.category_state).getName());
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getCategory_state_str() {
        return category_state_str;
    }

    public void setCategory_state_str(String category_state_str) {
        this.category_state_str = category_state_str;
    }
}
