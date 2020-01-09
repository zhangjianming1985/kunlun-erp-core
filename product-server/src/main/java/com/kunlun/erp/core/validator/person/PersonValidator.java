package com.kunlun.erp.core.validator.person;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.person.request.*;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName PersonValidator
 * @Description 人员数据校验
 * @Author Jm.zhang
 * @Date 2019-12-02 22:58
 * @Version 1.0
 **/
@Component(value = "person_validator")
public class PersonValidator extends AbstractValidator {

    public PersonValidator(){
        super.name_space= Urls.Company.NAMESPACE;
    }
    @Resource(name = "person_service")
    private PersonService person_service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonListRequest.class.isAssignableFrom(clazz) || PersonAddRequest.class.isAssignableFrom(clazz)
                ||PersonDetailRequest.class.isAssignableFrom(clazz)|| PersonUpdateRequest.class.isAssignableFrom(clazz) || LikeSearchPersonRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        if (target instanceof LikeSearchPersonRequest){
            return null;
        }
        return super.validatePermission(target);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof PersonListRequest){
            PersonListRequest request = (PersonListRequest)obj;
            error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code(),request.getBody().getCompany_type());
            if (error_code == null){
/*                if (request.getBody().getPerson_type() != SysConstant.PersonType.guides.getValue()){
                    //只能查询导游
                    error_code = ErrorCodeConstant.CONTACT_TYPE_INVALID;
                }*/
                if (SysConstant.PersonType.getPersonType(request.getBody().getPerson_type())==null){
                    //人员类型校验
                    error_code = ErrorCodeConstant.CONTACT_TYPE_INVALID;
                }
            }
        }else if (obj instanceof PersonAddRequest){
            PersonAddRequest request = (PersonAddRequest)obj;
            error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code(),request.getBody().getCompany_type());
            if (error_code == null){
                error_code = this.checkPersonType(request.getBody().getCompany_type(), request.getBody().getPerson_type());
            }
            if (error_code == null){
                error_code = this.checkPersonName(request.getBody().getPerson_name());
            }
            if (error_code == null){
                error_code = this.checkPersonMobile(request.getBody().getPerson_mobile());
            }
            if (error_code == null){
                error_code = this.checkPersonWechat(request.getBody().getPerson_wechat());
            }
            if (error_code == null){
                error_code = this.checkPersonQQ(request.getBody().getPerson_qq());
            }
            if (error_code == null){
                error_code = this.checkPersonPhone(request.getBody().getPerson_phone());
            }
            if (error_code == null){
                error_code = this.checkPersonPosition(request.getBody().getPosition());
            }
        }else if (obj instanceof PersonDetailRequest){
            PersonDetailRequest  request = (PersonDetailRequest)obj;
            PersonInfo record = person_service.getPersonByPersonCode(request.getBody().getPerson_code());
            if (record == null){
                error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
            }
        }else if (obj instanceof PersonUpdateRequest){
            PersonUpdateRequest  request = (PersonUpdateRequest)obj;
            if (StringUtils.isBlank(request.getBody().getPerson_code())){
                error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
            }
            if (error_code == null){
                PersonInfo record = person_service.getPersonByPersonCode(request.getBody().getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                }
                if (error_code == null){
                    if (record.getPerson_type()!=SysConstant.PersonType.guides.getValue()){
                        error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    }
                }
            }
        }else if (obj instanceof  LikeSearchPersonRequest){
            LikeSearchPersonRequest request = (LikeSearchPersonRequest)obj;
            error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code());
            if (error_code == null){
                error_code = this.checkPersonType(request.getBody().getPerson_type());
            }
        }


        return error_code;
    }

    /**
     * 校验人员类型
     * @param company_type
     * @param person_type
     * @return
     */
    public String checkPersonType(Integer company_type, Integer person_type){
        String error_code = this.checkPersonType(person_type);
        if (error_code == null){
            if (person_type == SysConstant.PersonType.guides.getValue() && company_type != SysConstant.CompanyType.supplier_guides.getValue()){
                //人员类型 和 公司类型不匹配。 导游 需关联 导服公司
                error_code = ErrorCodeConstant.CONTACT_TYPE_INVALID;
            }
        }
        return error_code;
    }

    /**
     * 校验人员类型
     * @param person_type
     * @return
     */
    public String checkPersonType(Integer person_type){
        String error_code = null;

        if (person_type ==null || SysConstant.PersonType.getPersonType(person_type)==null){
            error_code = ErrorCodeConstant.CONTACT_TYPE_INVALID;
        }
        return error_code;
    }

    /**
     * 校验人员姓名
     * @param person_name
     * @return
     */
    public String checkPersonName(String person_name){
        String error_code = null;
        if (RegexUtil.commonStrCheck(person_name,2,32,"~!@#$%^&*()+=-]")==false){
            error_code = ErrorCodeConstant.CONTACT_PERSON_NAME_INVALID;
        }
        return error_code;
    }

    /**
     * 校验手机号码
     * @param person_mobile
     * @return
     */
    public String checkPersonMobile(String person_mobile){
        String error_code = null;
        if (StringUtils.isNotBlank(person_mobile)){
            if (RegexUtil.isMobile(person_mobile) ==false){
                error_code = ErrorCodeConstant.CONTACT_MOBILE_INVALID;
            }
        }
        return error_code;
    }


    /**
     * 校验微信号
     * @param person_wechat
     * @return
     */
    public String checkPersonWechat(String person_wechat){
        String error_code = null;
        if (StringUtils.isNotBlank(person_wechat)){
            if (RegexUtil.isWeChat(person_wechat)==false){
                error_code = ErrorCodeConstant.CONTACT_WECHAT_INVALID;
            }
        }
        return error_code;
    }

    /**
     * 校验固话
     * @param person_phone
     * @return
     */
    public String checkPersonPhone(String person_phone){
        String error_code = null;
        if (StringUtils.isNotBlank(person_phone)){
            if (RegexUtil.isPhone(person_phone)==false){
                error_code = ErrorCodeConstant.CONTACT_PHONE_INVALID;
            }
        }
        return error_code;
    }

    /**
     * 校验QQ
     * @param person_qq
     * @return
     */
    public String checkPersonQQ(String person_qq){
        String error_code = null;
        if (StringUtils.isNotBlank(person_qq)){
            if (RegexUtil.isQQ(person_qq)==false){
                error_code = ErrorCodeConstant.CONTACT_QQ_INVALID;
            }
        }
        return error_code;
    }
    /**
     * 校验职务
     * @param person_position
     * @return
     */
    public String checkPersonPosition(String person_position){
        String error_code = null;
        if (StringUtils.isNotBlank(person_position)){
            if (RegexUtil.commonStrCheck(person_position,1,30,false) ==false){
                error_code = ErrorCodeConstant.CONTACT_POSITION_INVALID;
            }
        }
        return error_code;
    }
}
