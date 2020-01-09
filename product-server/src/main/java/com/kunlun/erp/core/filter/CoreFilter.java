package com.kunlun.erp.core.filter;

import com.alibaba.fastjson.TypeReference;
import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.common.util.LogUtil;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.DtoMessageUtil;
import com.kunlun.erp.core.dto.factory.CoreDtoFactory;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName CoreFilter
 * @Description 核心过滤
 * @Author Jm.zhang
 * @Date 2019/11/18 10:41
 * @Version 1.0
 **/
//@WebFilter(filterName = "coreFilter",urlPatterns = {"/*"})
public class CoreFilter implements Filter {
    @Resource(name = "dtoFactory")
    protected CoreDtoFactory dtoFactory;
    @Resource(name = "dto_message_util")
    protected DtoMessageUtil dto_message_util;

    public String url_path;
    public AbstractRequest request_dto = null;
    public AbstractResponse response_dto = null;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        HttpServletRequest http_request = (HttpServletRequest) request;
        url_path = http_request.getServletPath();
        String error_code = null;
        //获取post参数
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        String str =sb.toString();
        if (StringUtils.isBlank(str)){
            error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
        }
        if (error_code == null){
            try {
                request_dto = JsonUtil.toBean(str,new TypeReference<AbstractRequest>(){});
//                LogUtil.writeLogInfo(this.getClass().getName(), "onAccessDenied", "request_json"+"-"+url_path, JsonUtil.toStr(request_dto),message_type);
            }catch (Exception e){
                request_dto= null;
                error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
                e.printStackTrace();
            }
        }

        if (error_code != null){
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            try{
                response_dto = dtoFactory.createResponse();
                dto_message_util.setDtoErrorFromErrorCode(response_dto, error_code);
                response_dto.setBody("");
                String json_response = JsonUtil.toStr(response_dto);
                LogUtil.writeLogInfo(this.getClass().getName(), "doFilter", "response_json"+"-"+url_path, json_response, Urls.USER_NAME_SPACE);
                httpServletResponse.getWriter().write(json_response);
            }catch (Exception e){
                e.printStackTrace();
            }
            return;
        }else{
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
