package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.company.FinanceAccountDto;
import com.kunlun.erp.core.dto.company.FinanceDto;
import com.kunlun.erp.core.entity.FinancialAccount;
import com.kunlun.erp.core.service.company.FinancialAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName FinanceValidator
 * @Description 财务信息校验
 * @Author Jm.zhang
 * @Date 2019/11/19 10:38
 * @Version 1.0
 **/
@Component(value = "finance_validator")
public class FinanceValidator {
    @Resource(name = "financial_account_service")
    private FinancialAccountService financial_account_service;
    public String check(FinanceDto finance_dto,boolean is_create){
        String error_code = null;
        if (!is_create && finance_dto == null){
            //更新时，没有传参
            return null;
        }

        if (is_create && finance_dto==null){
            //创建时，没有传参
            error_code = ErrorCodeConstant.FINANCE_DATA_NULL;
        }
        if (error_code ==null){
            //结算模式
            if (finance_dto.getSettlement_mode() ==null || SysConstant.FinanceSettlementMode.getFinanceSettlementMode(finance_dto.getSettlement_mode()) ==null){
                error_code = ErrorCodeConstant.FINANCE_SETTLEMENT_MODE_INVALID;
            }
        }
        if (error_code ==null){
            //信用等级
            if (finance_dto.getCredit_level() == null || SysConstant.CreditLevel.getCreditLevel(finance_dto.getCredit_level()) ==null){
                error_code = ErrorCodeConstant.FINANCE_CREDIT_LEVEL_INVALID;
            }
        }
        if (error_code == null){
            //常用供应商
            if (finance_dto.getIs_common_use() == null  || SysConstant.FinanceCommonUsed.getFinanceCommonUsed(finance_dto.getIs_common_use()) ==null){
                error_code = ErrorCodeConstant.FINANCE_COMMON_USED_INVALID;
            }
        }

        if (error_code ==null){
            //金融账户
            if (finance_dto.getFinance_accounts()!=null && finance_dto.getFinance_accounts().size()>0){
                for (FinanceAccountDto account : finance_dto.getFinance_accounts()){
                    if (!is_create && StringUtils.isNotBlank(account.getAccount_code())){
                        //更新操作,检查金融账户编号是否存在
                        FinancialAccount fa_record = financial_account_service.getByAccountCode(account.getAccount_code());
                        if (fa_record == null){
                            error_code = ErrorCodeConstant.FINANCE_ACCOUNT_CODE_INVALID;
                            break;
                        }
                    }
                    //账户类型
                    if (SysConstant.FinanceAccountType.getFinanceAccountType(account.getAccount_type())==null){
                        error_code = ErrorCodeConstant.FINANCE_ACCOUNT_TYPE_INVALID;
                        break;
                    }
                    //账户名称
                    if (RegexUtil.commonStrCheck(account.getAccount_name(),1,60,"[、【】{}~!#$%^&*()+=-]")==false){
                        error_code = ErrorCodeConstant.FINANCE_ACCOUNT_NAME_INVALID;
                        break;
                    }
                    //账户号码
                    if (RegexUtil.commonStrCheck(account.getAccount_no(),1,50,"[、【】{}~!#$%^&*()+=-]")==false){
                        error_code = ErrorCodeConstant.FINANCE_ACCOUNT_NUMBER_INVALID;
                        break;
                    }
                    //账户开户行
                    if (account.getAccount_type()==SysConstant.FinanceAccountType.Bank.getValue()){
                        if (RegexUtil.commonStrCheck(account.getBank_name(),1,50,false)==false){
                            error_code = ErrorCodeConstant.FINANCE_ACCOUNT_BANK_NAME_INVALID;
                            break;
                        }
                    }
                    //默认设置
                    if (account.getIs_default()==null || account.getIs_default()>1){
                        error_code =ErrorCodeConstant.FINANCE_ACCOUNT_DEFAULT_INVALID;
                        break;
                    }

                }

            }
        }
        return error_code;


    }


}
