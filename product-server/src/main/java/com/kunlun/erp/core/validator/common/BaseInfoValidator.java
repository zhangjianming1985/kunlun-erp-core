package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.component.CompanyComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.request.CompanyAddReqDto;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.CompanyInfo;
import com.kunlun.erp.core.mapper.CompanyInfoMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName BaseInfoValidator
 * @Description 基础信息校验
 * @Author Jm.zhang
 * @Date 2019/11/18 20:09
 * @Version 1.0
 **/
@Component(value = "base_validator")
public class BaseInfoValidator extends AbstractValidator {
    @Resource(name = "component_company")
    private CompanyComponent component_company;
    @Resource
    private CompanyInfoMapper company_dao;

    /**
     * 校验公司类型
     * @param company_type
     * @return
     */
    public String checkCompanyType(Integer company_type){
        String error_code = null;
        if (SysConstant.CompanyType.getCompanyType(company_type)== null){
            error_code = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID;
        }
        return error_code;
    }

    /**
     * 企业唯一编号是否存在
     * @param company_code
     * @return
     */
    public String checkCompanyCode(String company_code){
        String error_code = null;
        CompanyInfo company_record = company_dao.selectByCompanyCode(company_code);
        if (company_record==null){
            error_code = ErrorCodeConstant.COMPANY_CODE_INVALID;
        }
        return error_code;
    }

    /**
     * 企业唯一编号是否存在
     * 企业数据是否与公司类型匹配
     * @param company_code
     * @return
     */
    public String checkCompanyCode(String company_code,Integer company_type,String trans_no,String secret_key,String per_key){
        String error_code = null;
        CompanyInfo company_record = company_dao.selectByCompanyCode(company_code);
        if (company_record==null){
            error_code = ErrorCodeConstant.COMPANY_CODE_INVALID;
        }
        if (error_code == null){
            if (company_record.getCompany_type()!=company_type){
                error_code = ErrorCodeConstant.COMPANY_CODE_INVALID;
            }
        }
        if (error_code == null){
            AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(trans_no,secret_key,per_key);
            if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(trans_no,secret_key, Urls.Company.NAMESPACE);
                if (company_record.getCreator_id()!=user_info.getBody().getUid()){
                    error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
                }
            }
        }
        return error_code;
    }

    /**
     * 企业唯一编号是否存在
     * 企业数据是否与公司类型匹配
     * @param company_code
     * @return
     */
    public String checkCompanyCode(String company_code,Integer company_type){
        String error_code = null;
        CompanyInfo company_record = company_dao.selectByCompanyCode(company_code);
        if (company_record==null){
            error_code = ErrorCodeConstant.COMPANY_CODE_INVALID;
        }
        if (error_code == null){
            if (company_record.getCompany_type()!=company_type){
                error_code = ErrorCodeConstant.COMPANY_CODE_INVALID;
            }
        }
        return error_code;
    }



    public String check(CompanyAddReqDto req_body){
        String error_code = this.checkCompanyType(req_body.getCompany_type());
        //所属平台
        if (error_code ==null){
            if (this.salesChannel(req_body.getCompany_type())){
                if (req_body.getCompany_type()==SysConstant.CompanyType.sales_channel_online.getValue() && SysConstant.BelongPlatform.getBelongPlatform(req_body.getBase_info().getBelong_platform())==null){
                    error_code = ErrorCodeConstant.COMPANY_BELONG_PLATFORM_IS_INVALID;
                }
            }
        }

        //经营许可证URL
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getBusiness_certificate())){
                if (req_body.getBase_info().getBusiness_certificate().length()>1000){
                    error_code = ErrorCodeConstant.BUSINESS_CERTIFICATE_URL_OUT_LENGTH;
                }
            }
        }
        //营业执照URL
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getBusiness_licence_url())){
                if (req_body.getBase_info().getBusiness_licence_url().length()>1000){
                    error_code = ErrorCodeConstant.BUSINESS_LICENCE_URL_OUT_LENGTH;
                }
            }
        }
        //详细地址
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getAddress())){
                if (req_body.getBase_info().getAddress().length()>80){
                    error_code = ErrorCodeConstant.COMPANY_ADDRESS_CHAR_OUT_LENGTH;
                }
            }
        }
        //简介
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getIntroduction())){
                if (req_body.getBase_info().getIntroduction().length()>1000){
                    error_code = ErrorCodeConstant.COMPANY_INTRODUCTION_CHAR_OUT_LENGTH;
                }
            }
        }

        //合作状态
        if (error_code == null){
            if (SysConstant.CooperationState.getCooperationState(req_body.getBase_info().getCooperation_state())== null){
                error_code = ErrorCodeConstant.COOPERATION_STATE_MISSING;
            }
        }
        //校验对接人
        if (error_code == null){
//            String contact_person = req_body.getBase_info().getContact_person();
        }
        //合同状态
        if (error_code == null){
            if (SysConstant.ContractDocumentState.getContractDocumentState(req_body.getBase_info().getContract_document_state())== null){
                error_code = ErrorCodeConstant.CONTRACT_DOCUMENT_STATE_MISSING;
            }

        }

        //合同附件URL
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getContract_document_url())){
                if (req_body.getBase_info().getContract_document_url().length()>1000){
                    error_code = ErrorCodeConstant.CONTRACT_DOCUMENT_URL_OUT_LENGTH;
                }
            }
        }

        //合作开始日期
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getCooperation_start_date())){
                if (RegexUtil.isDate(req_body.getBase_info().getCooperation_start_date())==false){
                    error_code = ErrorCodeConstant.COOPERATION_START_DATE_FORMAT_MISSING;
                }
            }
        }

        //合作结束日期
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getCooperation_end_date())){
                if (RegexUtil.isDate(req_body.getBase_info().getCooperation_end_date())==false){
                    error_code = ErrorCodeConstant.COOPERATION_END_DATE_FORMAT_MISSING;
                }
            }
        }

        //备注
        if (error_code == null){
            if (StringUtils.isNotBlank(req_body.getBase_info().getRemarks())){
                if (req_body.getBase_info().getRemarks().length()>1000){
                    error_code = ErrorCodeConstant.REMARKS_OUT_LENGTH;
                }
            }
        }
        //公司名称重复校验
        if (error_code == null){
            int count =component_company.countByCompanyName(req_body.getBase_info().getCompany_name());
            if (count >0){
                error_code = ErrorCodeConstant.COMPANY_NAME_EXIST;
            }
        }





        return error_code;
    }

    /**
     * 是否销售渠道
     * @param company_type
     * @return
     */
    public boolean salesChannel(Integer company_type){
        return company_type == SysConstant.CompanyType.sales_channel_online.getValue()
                || company_type == SysConstant.CompanyType.sales_channel_offline.getValue()
                || company_type == SysConstant.CompanyType.sales_channel_other.getValue();
    }


    @Override
    public String myValidate(Object obj) {
        return null;
    }
}
