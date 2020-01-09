package com.kunlun.erp.core.filter.test;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName Myfilter
 * @Description
 * @Author Jm.zhang
 * @Date 2019-11-10 22:46
 * @Version 1.0
 **/
@WebFilter(filterName = "myFilter",urlPatterns = {"/filter/my/1/*","/filter/my/2/*"})
public class Myfilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入 my Filter");
        chain.doFilter(request,response);
        System.out.println("离开 my Filter");
    }

    @Override
    public void destroy() {

    }
}
