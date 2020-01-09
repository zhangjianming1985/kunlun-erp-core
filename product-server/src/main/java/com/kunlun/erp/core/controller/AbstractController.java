package com.kunlun.erp.core.controller;

import com.kunlun.erp.core.dto.DtoMessageUtil;
import com.kunlun.erp.core.dto.factory.CoreDtoFactory;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;

/**
 * @ClassName AbstractController
 * @Description
 * @Author Jm.zhang
 * @Date 2019-11-18 1:57
 * @Version 1.0
 **/
public abstract class AbstractController {
    @Resource(name = "dto_message_util")
    protected DtoMessageUtil dto_message_util;
    @Resource(name = "dtoFactory")
    protected CoreDtoFactory dtoFactory;
    protected Validator validator;
    public AbstractController(Validator validator) {
        this.validator = validator;
    }

    @InitBinder()
    public void initBinder(DataBinder binder) {
        binder.addValidators(validator);
    }
}
