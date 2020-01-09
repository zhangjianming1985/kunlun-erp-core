package com.kunlun.erp.core.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MyHandlerInterceptor
 * @Description CORS跨域拦截器
 * @Author Jm.zhang
 * @Date 2019/12/4 11:22
 * @Version 1.0
 **/
@Component
public class CorsHandlerInterceptor implements HandlerInterceptor {
    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {

        String origin= request.getHeader("Origin");
        System.out.println("------preHandle-----origin = " +origin);
        if (StringUtils.isNotBlank(origin) && !origin.equals("null")) {
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        }
        if ("OPTIONS".equals(request.getMethod())){
            response.getOutputStream().write("".getBytes());
            return false;
        }
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("------postHandle-----");
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("------afterCompletion-----");

    }

}

