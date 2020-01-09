package com.kunlun.erp.core.dto.company.response;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.company.FinanceDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName FinanceRespDto
 * @Description 财务响应dto
 * @Author Jm.zhang
 * @Date 2019/11/28 16:14
 * @Version 1.0
 **/
public class FinanceRespDto extends FinanceDto {
    @ApiModelProperty(value = "结算周期说明",example = "月结")
    private String  settlement_mode_str;

    @ApiModelProperty(value = "是否常用",example = "常用")
    private String is_common_use_str;

    public String getSettlement_mode_str() {
        return SysConstant.FinanceSettlementMode.getFinanceSettlementMode(super.getSettlement_mode()).getName();
    }

    public void setSettlement_mode_str(String settlement_mode_str) {
        this.settlement_mode_str = settlement_mode_str;
    }

    public String getIs_common_use_str() {
        return SysConstant.FinanceCommonUsed.getFinanceCommonUsed(super.getIs_common_use()).getName();
    }

    public void setIs_common_use_str(String is_common_use_str) {
        this.is_common_use_str = is_common_use_str;
    }
}
