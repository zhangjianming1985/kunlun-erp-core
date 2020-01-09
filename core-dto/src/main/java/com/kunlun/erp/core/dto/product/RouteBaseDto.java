package com.kunlun.erp.core.dto.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName RouteBaseDto
 * @Description 线路产品基本信息
 * @Author Jm.zhang
 * @Date 2019/12/7 14:11
 * @Version 1.0
 **/
@ApiModel(description = "线路产品基本数据")
public class RouteBaseDto {
    @ApiModelProperty(value = "内部代码",example = "LP58654145")
    private String internal_code;

    @ApiModelProperty(required = true,value = "是否购物：0=纯玩不购物、1=购物、2=推荐购物；",example = "0")
    @NotNull(message = ErrorCodeConstant.PRODUCT_IS_SHOPPING_INVALID)
    private Integer is_shopping;

    @ApiModelProperty(required = true,value = "是否有自费:0=无自费、1=有自费、2=推荐自费",example = "0")
    @NotNull(message = ErrorCodeConstant.PRODUCT_OWNER_EXPENSE_INVALID)
    private Integer owner_expense;

    @ApiModelProperty(value = "逗号分割，0=标准间、1=大床房、2=三人间、3=陪房、4=不用房",example = "0,1,2,3")
//    @NotNull(message = ErrorCodeConstant.PRODUCT_ROOM_TYPE_INVALID)
    private String room_type;

    @ApiModelProperty(value = "逗号分割，保险信息 0=意外险、1=责任险、2=统一险、3=线路含保险",example = "0,1,2")
//    @NotNull(message = ErrorCodeConstant.PRODUCT_INSURANCE_TYPE_INVALID)
    private String insurance_type;

    public String getInternal_code() {
        return internal_code==null?"":internal_code;
    }

    public void setInternal_code(String internal_code) {
        this.internal_code = internal_code==null?"":internal_code;
    }

    public Integer getIs_shopping() {
        return is_shopping;
    }

    public void setIs_shopping(Integer is_shopping) {
        this.is_shopping = is_shopping;
    }

    public Integer getOwner_expense() {
        return owner_expense;
    }

    public void setOwner_expense(Integer owner_expense) {
        this.owner_expense = owner_expense;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(String insurance_type) {
        this.insurance_type = insurance_type;
    }
}
