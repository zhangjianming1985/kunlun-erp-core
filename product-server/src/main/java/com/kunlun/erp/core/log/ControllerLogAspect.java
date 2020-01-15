package com.kunlun.erp.core.log;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.LogUtil;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.service.account.AccountService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ControllerLogAspect
 * @Description 日志处理
 * @Author Jm.zhang
 * @Date 2019-11-17 11:48
 * @Version 1.0
 **/
@Component
@Aspect
public class ControllerLogAspect {
    @Resource(name = "account_service")
    private AccountService account_service;
    @Around("within(com.kunlun.erp.core.controller..*) && @annotation(systemLog)")
    public Object doAroundMethod(ProceedingJoinPoint pjd, SystemLog systemLog) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String request_url = request.getRequestURI();
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 请求方法
        String invoke_method = pjd.getSignature().toShortString();
        // 执行方法，获取返回参数
        Object respDto = pjd.proceed();
        // 结束时间
        long endTime = System.currentTimeMillis();// 结束时间
        // 执行时长
        float excTime = (float) (endTime - startTime) / 1000;
        handleLog(request_url,invoke_method, String.valueOf(startTime), pjd.getArgs(), respDto, String.valueOf(endTime),
                String.valueOf(excTime),systemLog);
        return respDto;

    }


    private void handleLog(String request_url,String methodName, String beginDate, Object[] reqParams, Object respDto, String endDate,
                           String consumeTimes, SystemLog systemLog)  {
        String log_level=SysConstant.LogLevel.info;
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("<=================================>").append("\n");
        sb.append("[request message]:").append("\n");
        sb.append("	[call method]:").append(methodName).append("\n");
        sb.append("	[begin time]:").append(DateUtil.millisToStringByFormat(DateUtil.FORMATTER_DATE_TIME_DETAIL, Long.valueOf(beginDate))).append("\n");
        String login_name="";
        String user_name="";
        // 处理请求参数
        if (reqParams != null && reqParams[0] instanceof AbstractRequest) {
            AbstractRequest request = (AbstractRequest)reqParams[0];
            AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),Urls.USER_NAME_SPACE);
            if (user_info!=null && user_info.getHeader().getState().equals(SysConstant.RespStatus.resp_status_success.getValue())){
                login_name=user_info.getBody().getLoginName();
                user_name=user_info.getBody().getUserName();
            }
            sb.append("	[req header]:").append(JsonUtil.toStr(request.getHeader())).append("-"+login_name+"|"+user_name).append("\n");
            sb.append("	[req body]:").append(JsonUtil.toStr(request.getBody())).append("\n");
            sb.append("\n");
        }else {
            sb.append("	[request content]:").append(JsonUtil.toStr(reqParams)).append("\n");
        }
        sb.append("[response message]:").append("\n");
        // 处理响应结果
        if (respDto instanceof AbstractResponse) {
            AbstractResponse response = (AbstractResponse)respDto;
            sb.append("	[resp header]:").append(JsonUtil.toStr(response.getHeader())).append("-"+login_name+"|"+user_name).append("\n");
            sb.append("	[resp body]:");
            if (systemLog.show_resp_body()){
                sb.append(JsonUtil.toStr(response.getBody())).append("\n");
            }else{
                sb.append("not show").append("\n");
            }
            if (response.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                log_level = SysConstant.LogLevel.error;
            }
        }else {
            sb.append("	[response content]:").append(JsonUtil.toStr(respDto)).append("\n");
        }
        sb.append("	[end time]:").append(DateUtil.millisToStringByFormat(DateUtil.FORMATTER_DATE_TIME_DETAIL, Long.valueOf(endDate))).append("\n");
        sb.append("	[time counsuming]:").append(consumeTimes).append("\n");
        sb.append("<=================================>").append("\n");
        LogUtil logUtil = LogUtil.getInstance(Urls.getUrlNameSpace(request_url));
       if (log_level.equals(SysConstant.LogLevel.error)){
            logUtil = LogUtil.getInstance(Urls.getUrlNameSpace(""));
        }
        switch (log_level){
            case SysConstant.LogLevel.info:
                logUtil.logger.info(sb.toString());
                break;
            case SysConstant.LogLevel.debug:
                logUtil.logger.debug(sb.toString());
                break;
            case SysConstant.LogLevel.warning:
                logUtil.logger.warn(sb.toString());
                break;
            case SysConstant.LogLevel.error:
                logUtil.logger.error(sb.toString());
                break;
            case  SysConstant.LogLevel.trace:
                logUtil.logger.trace(sb.toString());
                break;
        }

    }
}
