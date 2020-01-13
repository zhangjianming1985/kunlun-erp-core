package com.kunlun.erp.core.validator.company;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.company.request.*;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

/**
 * @ClassName CompanyValidator
 * @Description 供应商校验器
 * @Author Jm.zhang
 * @Date 2019/11/18 15:54
 * @Version 1.0
 **/
@Component(value = "company_validator")
public class CompanyValidator extends AbstractValidator {

    public CompanyValidator() {
        super.name_space = Urls.Company.NAMESPACE;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyAddRequest.class.isAssignableFrom(clazz) || CompanyListRequest.class.isAssignableFrom(clazz)
                || CompanyDetailRequest.class.isAssignableFrom(clazz)||CompanyEditRequest.class.isAssignableFrom(clazz)
                ||LikeSearchNameRequest.class.isAssignableFrom(clazz)||CompanyDeleteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        if (target instanceof  LikeSearchNameRequest){
            return null;
        }
        return super.validatePermission(target);
    }

    @Override
    public String myValidate(Object target) {
        String error_code = null;
        if (target instanceof  CompanyAddRequest){
            CompanyAddRequest request = (CompanyAddRequest)target;
            CompanyAddReqDto req_body = request.getBody();
            error_code = base_validator.check(req_body);
            if (error_code ==null){
                error_code = area_validator.check(req_body.getArea_info(),true);
            }
            if (error_code == null){
                error_code = leader_validator.check(req_body.getLeader_info());
            }
            if (error_code ==null){
                error_code = finance_validator.check(req_body.getFinancial_info(),true);
            }
            if (error_code ==null){
                error_code = contact_validator.check(req_body.getContact_info(),true);
            }
            if (error_code == null){
                if (base_validator.salesChannel(req_body.getCompany_type())){
                    error_code= sales_channel_cost_validator.check(req_body.getSales_channel_cost(),true);
                }
            }

        }else if (target instanceof CompanyListRequest){
            CompanyListRequest request = (CompanyListRequest)target;
            CompanyListReqDto req_body = request.getBody();
            error_code = base_validator.checkCompanyType(req_body.getCompany_type());
        }else if (target  instanceof  CompanyDetailRequest){
            CompanyDetailRequest request = (CompanyDetailRequest)target;
            error_code = base_validator.checkCompanyType(request.getBody().getCompany_type());
            if (error_code == null){
                error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code(),request.getBody().getCompany_type(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            }
        }else if (target  instanceof  CompanyEditRequest){
            CompanyEditRequest request = (CompanyEditRequest)target;
            error_code = base_validator.checkCompanyType(request.getBody().getCompany_type());
            if (error_code == null){
                error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code(),request.getBody().getCompany_type(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            }
            if (error_code == null){
                error_code = area_validator.check(request.getBody().getArea_info(),false);
            }
            if (error_code == null){
                error_code = leader_validator.check(request.getBody().getLeader_info());
            }
            if (error_code ==null){
                error_code = finance_validator.check(request.getBody().getFinancial_info(),false);
            }
            if (error_code ==null){
                error_code = contact_validator.check(request.getBody().getContact_info(),false);
            }
            if (error_code == null){
                if (base_validator.salesChannel(request.getBody().getCompany_type())){
                    error_code= sales_channel_cost_validator.check(request.getBody().getSales_channel_cost_info(),false);
                }
            }
        }else if (target instanceof  LikeSearchNameRequest){
            LikeSearchNameRequest  request = (LikeSearchNameRequest)target;
            if (SysConstant.CompanyType.getCompanyType(request.getBody().getCompany_type())==null){
                error_code = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID;
            }
        }else if (target instanceof  CompanyDeleteRequest){
            CompanyDeleteRequest request = (CompanyDeleteRequest)target;
            error_code = base_validator.checkCompanyType(request.getBody().getCompany_type());
            if (error_code == null){
                error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code(),request.getBody().getCompany_type(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            }
        }

        return error_code;
    }



}
