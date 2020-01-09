package com.kunlun.erp.core.validator.test;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.test.TestDto;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestValidator
 * @Description
 * @Author Jm.zhang
 * @Date 2019-11-18 2:02
 * @Version 1.0
 **/
@Component(value = "testValidator")
public class TestValidator extends AbstractValidator {


    @Override
    public String validatePermission(Object target) {
        return null;
    }

    @Override
    public String myValidate(Object target) {
        String error_code = null;
        Object request_body = ((AbstractRequest) target).getBody();
        TestDto dto = (TestDto)request_body;
        if (StringUtils.isBlank(dto.getName())){
            error_code = ErrorCodeConstant.test_code_a;
        }


        return error_code;
    }


}
