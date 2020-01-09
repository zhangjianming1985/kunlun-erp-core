package com.kunlun.erp.core.dto;

import com.alibaba.fastjson.TypeReference;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.json.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName DtoMessageUtil
 * @Description
 * @Author Jm.zhang
 * @Date 2019/11/15 18:18
 * @Version 1.0
 **/
@Component(value = "dto_message_util")
public class DtoMessageUtil {
    @Resource
    private org.springframework.context.MessageSource messageSource;

    /**
     * 设置错误代码
     * @param response
     * @param errorCode
     * @param <T>
     * @return
     */
    public <T> AbstractResponse<T> setDtoErrorFromErrorCode(AbstractResponse<T> response, String errorCode) {
        return setDtoErrorFromErrorCode(response, errorCode, "");
    }

    /**
     * 设置错误代码，可选消息后追加
     * @param response
     * @param errorCode
     * @param complementraryMsg
     * @param <T>
     * @return
     */
    public <T> AbstractResponse<T> setDtoErrorFromErrorCode(AbstractResponse<T> response, String errorCode, String complementraryMsg) {
        Map<String, String> errMap = response.getHeader().getError_message();
        StringBuilder msg = new StringBuilder(messageSource.getMessage(errorCode, null, Locale.CHINA) );
        if (StringUtils.isNotBlank(complementraryMsg)){
            msg.append( " : " + complementraryMsg);
        }
        errMap.put(errorCode, msg.toString());

        response.getHeader().setState(SysConstant.RespStatus.resp_status_fail.getValue());
        return response;
    }

    public Map<String, String> getErrorsMessage(String errorCode, String[] values) {
        Map<String, String> errorsMap = new HashMap<>();
        String msg = messageSource.getMessage(errorCode, null, Locale.CHINA);
        for (int i = 0; i < values.length; i++) {
            msg = msg.replace("{" + i + "}", values[i]);
        }
        errorsMap.put(errorCode, msg);
        return errorsMap;
    }


    /**
     * 设置错误代码，从validator校验结果
     * @param regResp
     * @param result
     * @param <T>
     * @return
     */
    public <T> AbstractResponse<T> setDtoErrorFromResult(AbstractResponse<T> regResp, BindingResult result) {
        return setDtoErrorFromResult(regResp, result, null);
    }

    /**
     * 设置错误代码，从validator校验结果
     * @param regResp
     * @param result
     * @param args
     * @param <T>
     * @return
     */
    public <T> AbstractResponse<T> setDtoErrorFromResult(AbstractResponse<T> regResp, BindingResult result, Object[] args) {
        if (result.hasErrors()) {
            Map<String, String> errMap = regResp.getHeader().getError_message();
            List<ObjectError> errors = result.getAllErrors();
            String msg = null;
            String code = null;
            for (ObjectError err : errors) {
                try{
                    code = err.getDefaultMessage();
                    msg = messageSource.getMessage(code, args, Locale.CHINA);
                }catch (Exception e){
                        msg = null;
                }
                //非本地code，那么就是系统内部API调用的返回
                if (StringUtils.isBlank(msg)){
                    AbstractResponse response = JsonUtil.toBean(code,new TypeReference<AbstractResponse>(){});
                    if (response.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                        for(Map.Entry<String, String> entry : response.getHeader().getError_message().entrySet()){
                            errMap.put(entry.getKey(),entry.getValue());
                        }
                    }
                }else{
                    errMap.put(code, msg);
                }

            }
        }
        regResp.getHeader().setState(SysConstant.RespStatus.resp_status_fail.getValue());
        return regResp;
    }


    public Map<String, String> getErrorsMessage(String errorCode) {
        Map<String, String> errorsMap = new HashMap<>();
        String msg = messageSource.getMessage(errorCode, null, Locale.CHINA);
        errorsMap.put(errorCode, msg);
        return errorsMap;
    }
}
