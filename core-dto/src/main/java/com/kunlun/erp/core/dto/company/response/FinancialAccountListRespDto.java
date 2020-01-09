package com.kunlun.erp.core.dto.company.response;

import com.kunlun.erp.core.dto.company.FinanceAccountDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName FinancialAccountListRespDto
 * @Description 获取金融账户响应
 * @Author Jm.zhang
 * @Date 2019/12/16 12:37
 * @Version 1.0
 **/
@ApiModel(description = "获取金融账户响应")
public class FinancialAccountListRespDto {

    private List<FinanceAccountDto> account_data;

    public List<FinanceAccountDto> getAccount_data() {
        return account_data;
    }

    public void setAccount_data(List<FinanceAccountDto> account_data) {
        this.account_data = account_data;
    }
}
