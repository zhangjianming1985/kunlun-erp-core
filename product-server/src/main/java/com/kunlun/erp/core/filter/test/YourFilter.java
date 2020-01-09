package com.kunlun.erp.core.filter.test;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName YourFilter
 * @Description
 * @Author Jm.zhang
 * @Date 2019-11-10 22:47
 * @Version 1.0
 **/
public class YourFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入 your Filter");
        chain.doFilter(request,response);
        System.out.println("离开 your Filter");
    }

    @Override
    public void destroy() {

    }
}
