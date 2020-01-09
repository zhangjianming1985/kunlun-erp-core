package com.kunlun.erp.core.validator.financial;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.company.request.FinancialAccountListRequest;
import com.kunlun.erp.core.dto.finance.request.*;
import com.kunlun.erp.core.mapper.RouteHallMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import com.kunlun.erp.core.validator.routeHall.RouteHallValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName FinancialValidator
 * @Description 财务校验器
 * @Author Jm.zhang
 * @Date 2019/12/16 12:52
 * @Version 1.0
 **/
@Component(value = "financial_validator")
public class FinancialValidator extends AbstractValidator {
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;

    @Resource
    private RouteHallMapper hall_dao;
    public FinancialValidator(){
        super.name_space= Urls.Company.NAMESPACE;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FinancialAccountListRequest.class.isAssignableFrom(clazz) || HallProductEndListRequest.class.isAssignableFrom(clazz)
                || FinanceAuditRequest.class.isAssignableFrom(clazz) || FinanceAuditResultRequest.class.isAssignableFrom(clazz) || CollectedListRequest.class.isAssignableFrom(clazz)
                || PaymentListRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        if (target instanceof  FinancialAccountListRequest){
            return null;
        }
        return super.validatePermission(target);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code = null;
        if (obj instanceof FinancialAccountListRequest){
            FinancialAccountListRequest  request = (FinancialAccountListRequest)obj;
            error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code());
        }else if (obj instanceof  FinanceAuditRequest){
            FinanceAuditRequest request = (FinanceAuditRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code ==null){
                if (SysConstant.FinanceAuditStatus.getFinanceAuditStatus(request.getBody().getApprove_state())==null){
                    error_code= ErrorCodeConstant.ROUTE_AUDIT_STATUS_INVALID;
                }
            }
        }else if ( obj instanceof  FinanceAuditResultRequest){
            FinanceAuditResultRequest request = (FinanceAuditResultRequest)obj;
            error_code = route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }

        return error_code;
    }
}
