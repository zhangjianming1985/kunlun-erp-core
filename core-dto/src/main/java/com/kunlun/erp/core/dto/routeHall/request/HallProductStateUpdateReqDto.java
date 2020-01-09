package com.kunlun.erp.core.dto.routeHall.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName HallProductStateUpdateReqDto
 * @Description 修改线路大厅产品状态
 * @Author Jm.zhang
 * @Date 2019-12-25 23:57
 * @Version 1.0
 **/
@ApiModel(description = "修改线路大厅产品状态")
public class HallProductStateUpdateReqDto {
    @ApiModelProperty(required = true,value = "团号",example = "13000001397141792")
    @NotBlank(message = ErrorCodeConstant.HALL_DAILY_CODE_INVALID)
    private String group_code;

    @ApiModelProperty(required = true,value = "状态码，3=行程取消、4=删除、5=恢复行程",example ="3" )
    @NotNull(message = ErrorCodeConstant.HALL_DAILY_STATE_INVALID)
    @Range(min = 3,max = 5,message = ErrorCodeConstant.HALL_DAILY_STATE_INVALID)
    private Integer state;

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
