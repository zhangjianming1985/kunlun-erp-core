package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.company.FinanceAccountDto;
import com.kunlun.erp.core.dto.condition.FinancialAccountCondition;
import com.kunlun.erp.core.entity.FinancialAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinancialAccountMapper {

    int insertSelective(FinancialAccount record);

    int updateByPrimaryKeySelective(FinancialAccount record);

    int deleteByPrimaryKey(Integer id);

    int deleteByCompanyCode(String company_code);

    void deleteByCondition(FinancialAccountCondition condition);


    FinancialAccount selectByPrimaryKey(Integer id);

    FinancialAccount selectByAccountCode(@Param("account_code") String account_code);

    List<FinancialAccount> selectByCompanyCode(@Param("company_code") String company_code);

    List<FinanceAccountDto> selectDtoByCompanyCode(@Param("company_code") String company_code);
}