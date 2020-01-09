package com.kunlun.erp.core.service.company;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.request.FinancialAccountListRequest;
import com.kunlun.erp.core.dto.company.response.FinancialAccountListRespDto;
import com.kunlun.erp.core.dto.condition.FinancialAccountCondition;
import com.kunlun.erp.core.entity.FinancialAccount;

import java.util.List;

/**
 * @ClassName FinancialAccountService
 * @Description 金融账户业务服务接口
 * @Author Jm.zhang
 * @Date 2019/11/28 16:57
 * @Version 1.0
 **/
public interface FinancialAccountService {
    FinancialAccount getByAccountCode(String account_code);

    List<FinancialAccount> getListByCompanyCode(String company_code);

    int insertAccount(FinancialAccount account);

    int deleteByCompanyCode(String company_code);

    int updateAccount(FinancialAccount record);

    void deleteByCondition(FinancialAccountCondition condition);

    AbstractResponse<FinancialAccountListRespDto> list(FinancialAccountListRequest request) throws Exception;
}
