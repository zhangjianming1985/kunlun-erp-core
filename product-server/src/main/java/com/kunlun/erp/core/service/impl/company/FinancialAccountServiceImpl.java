package com.kunlun.erp.core.service.impl.company;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.FinanceAccountDto;
import com.kunlun.erp.core.dto.company.request.FinancialAccountListRequest;
import com.kunlun.erp.core.dto.company.response.FinancialAccountListRespDto;
import com.kunlun.erp.core.dto.condition.FinancialAccountCondition;
import com.kunlun.erp.core.entity.FinancialAccount;
import com.kunlun.erp.core.mapper.FinancialAccountMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.company.FinancialAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName FinancialAccountServiceImpl
 * @Description 金融账户业务服务实现
 * @Author Jm.zhang
 * @Date 2019/11/28 16:58
 * @Version 1.0
 **/
@Service(value = "financial_account_service")
public class FinancialAccountServiceImpl extends BaseService implements FinancialAccountService {
    @Resource
    private FinancialAccountMapper financial_account_dao;


    @Override
    public FinancialAccount getByAccountCode(String account_code) {
        return financial_account_dao.selectByAccountCode(account_code);
    }

    @Override
    public List<FinancialAccount> getListByCompanyCode(String company_code) {
        return financial_account_dao.selectByCompanyCode(company_code);
    }

    @Override
    public int insertAccount(FinancialAccount account) {
        return financial_account_dao.insertSelective(account);
    }

    @Override
    public int deleteByCompanyCode(String company_code) {
        return financial_account_dao.deleteByCompanyCode(company_code);
    }

    @Override
    public int updateAccount(FinancialAccount record) {
        return financial_account_dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByCondition(FinancialAccountCondition condition) {
         financial_account_dao.deleteByCondition(condition);
    }

    @Override
    public AbstractResponse<FinancialAccountListRespDto> list(FinancialAccountListRequest request) {
        AbstractResponse<FinancialAccountListRespDto> response = dtoFactory.createResponse(request.getHeader());
        FinancialAccountListRespDto resp_body = new FinancialAccountListRespDto();
        List<FinanceAccountDto> list = financial_account_dao.selectDtoByCompanyCode(request.getBody().getCompany_code());
        resp_body.setAccount_data(list);
        response.setBody(resp_body);
        return response;
    }
}
