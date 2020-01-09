package com.kunlun.erp.core.listener.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName MyListener
 * @Description 测试监听一
 * @Author Jm.zhang
 * @Date 2019-11-10 22:48
 * @Version 1.0
 **/
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("进入 my listener  contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
