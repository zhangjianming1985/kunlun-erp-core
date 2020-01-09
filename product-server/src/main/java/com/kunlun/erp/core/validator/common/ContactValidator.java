package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.person.PersonDto;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.person.PersonValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ContactValidator
 * @Description 联系人信息校验
 * @Author Jm.zhang
 * @Date 2019/11/19 11:58
 * @Version 1.0
 **/
@Component(value = "contact_validator")
public class ContactValidator {
    @Resource(name = "person_service")
    private PersonService person_service;

    @Resource(name = "person_validator")
    private PersonValidator person_validator;
    public String check(List<PersonDto> person_list,boolean is_create){
        String error_code = null;
        if (person_list ==null || person_list.isEmpty()) return null;
        for (PersonDto person: person_list){
            if (!is_create && StringUtils.isNotBlank(person.getPerson_code())){
                PersonInfo person_record = person_service.getPersonByPersonCode(person.getPerson_code());
                if (person_record == null){
                    error_code=ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }

            }
            //人员类型
            if (SysConstant.PersonType.getPersonType(person.getPerson_type())==null){
                error_code = ErrorCodeConstant.CONTACT_TYPE_INVALID;
                break;
            }
            //姓名
            error_code = person_validator.checkPersonName(person.getPerson_name());
            if (error_code != null)break;
            //手机号
            error_code = person_validator.checkPersonMobile(person.getPerson_mobile());
            if (error_code != null)break;
            //微信号
            error_code = person_validator.checkPersonWechat(person.getPerson_wechat());
            if (error_code != null)break;
            //固话
            error_code =person_validator.checkPersonPhone(person.getPerson_phone());
            if (error_code != null)break;
            //QQ
            error_code = person_validator.checkPersonQQ(person.getPerson_qq());
            if (error_code != null)break;
            //职务
            error_code = person_validator.checkPersonPosition(person.getPosition());
            if (error_code != null)break;
        }
        return error_code;

    }

}
