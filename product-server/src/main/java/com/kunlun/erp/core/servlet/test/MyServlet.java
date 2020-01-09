package com.kunlun.erp.core.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyServlet
 * @Description 测试servlet一
 * @Author Jm.zhang
 * @Date 2019-11-10 22:50
 * @Version 1.0
 **/
@WebServlet(name = "myServlet" ,urlPatterns = "/servlet/my/*")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("myServlet url = "+req.getRequestURL().toString());
        super.doGet(req, resp);
    }
}
