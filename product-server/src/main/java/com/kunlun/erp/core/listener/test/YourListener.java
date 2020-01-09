package com.kunlun.erp.core.listener.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName YourListener
 * @Description 测试监听二
 * @Author Jm.zhang
 * @Date 2019-11-10 22:49
 * @Version 1.0
 **/
public class YourListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("进入 your listener  contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
