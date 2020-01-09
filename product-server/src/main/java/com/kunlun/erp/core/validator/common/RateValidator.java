package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.common.RateRequest;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

/**
 * @ClassName RateValidator
 * @Description 汇率转换校验器
 * @Author Jm.zhang
 * @Date 2020/1/2 10:52
 * @Version 1.0
 **/
@Component(value = "rate_validator")
public class RateValidator extends AbstractValidator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        return null;
    }

    @Override
    public String myValidate(Object obj) {
        String error_code = null;
        if (obj instanceof RateRequest){
            RateRequest request = (RateRequest)obj;
            if (!request.getBody().getSource_currency().equals(SysConstant.Currency.USD.getValue())){
                error_code = ErrorCodeConstant.SOURCE_CURRENCY_INVALID;
            }
            if (error_code == null){
                if (!request.getBody().getTarget_currency().equals(SysConstant.Currency.CNY.getValue())){
                    error_code = ErrorCodeConstant.TARGET_CURRENCY_INVALID;
                }
            }
        }
        return error_code;
    }
}
