package com.kunlun.erp.core.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName YourServlet
 * @Description 测试SERVLET二
 * @Author Jm.zhang
 * @Date 2019-11-10 22:50
 * @Version 1.0
 **/
public class YourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("YourFilter url = " + req.getRequestURL());
        super.doGet(req, resp);
    }
}
