package com.kunlun.erp.core.test;

import com.kunlun.erp.core.common.configuration.GlobalProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.LogUtil;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.test.TestDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.validator.test.TestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @ClassName MyController
 * @Description 测试Controller
 * @Author Jm.zhang
 * @Date 2019-11-10 22:40
 * @Version 1.0
 **/
@Controller
public class MyController extends AbstractController {
    public MyController(TestValidator validator) {
        super(validator);
    }
    @Autowired
    private GlobalProperties config;

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        Map<String,String> msg = dto_message_util.getErrorsMessage("001");
        System.out.println("msg = " +msg.toString());
        System.out.println("config :"+config.getAccount_api_url());
        LogUtil.writeLogInfo(this.getClass().getName(),"hello()","msg",msg.toString(), Urls.SalesChannel.NAMESPACE);
        return "hello my friend !"+ SysConstant.LogLevel.info;
    }

    @SystemLog
    @RequestMapping(value = Urls.test,method = RequestMethod.POST)
    public @ResponseBody AbstractResponse<TestDto> aop(@RequestBody @Valid AbstractRequest<TestDto> request, BindingResult result) {
        AbstractResponse<TestDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
