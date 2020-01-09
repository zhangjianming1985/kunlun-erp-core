package com.kunlun.erp.core.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName FinanceDto
 * @Description 财务信息
 * @Author Jm.zhang
 * @Date 2019/11/18 14:44
 * @Version 1.0
 **/
@ApiModel(description = "财务信息")
public class FinanceDto {


    @ApiModelProperty(required = true,value = "财务结算模式：0=现结（实时）结算、1=周结、2=月结、3=季结、4=年结",example = "2")
    private Integer settlement_mode;

    @ApiModelProperty(required = true,value = "A、B、C、D 四个级别，默认D；",example = "C")
    private String credit_level;

    @ApiModelProperty(required = true,value = "常用供应商 0=不常用、1=常用",example = "1")
    private Integer is_common_use;

    private List<FinanceAccountDto> finance_accounts;



    public Integer getSettlement_mode() {
        return settlement_mode;
    }

    public void setSettlement_mode(Integer settlement_mode) {
        this.settlement_mode = settlement_mode;
    }

    public String getCredit_level() {
        return credit_level;
    }

    public void setCredit_level(String credit_level) {
        this.credit_level = credit_level;
    }

    public Integer getIs_common_use() {
        return is_common_use;
    }

    public void setIs_common_use(Integer is_common_use) {
        this.is_common_use = is_common_use;
    }

    public List<FinanceAccountDto> getFinance_accounts() {
        return finance_accounts;
    }

    public void setFinance_accounts(List<FinanceAccountDto> finance_accounts) {
        this.finance_accounts = finance_accounts;
    }

}
