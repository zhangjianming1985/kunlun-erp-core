package com.kunlun.erp.core;

import com.kunlun.erp.core.filter.test.YourFilter;
import com.kunlun.erp.core.listener.test.YourListener;
import com.kunlun.erp.core.servlet.test.YourServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ProductServerRun
 * @Description 产品服务启动
 * @Author Jm.zhang
 * @Date 2019-11-10 22:26
 * @Version 1.0
 **/
@SpringBootApplication
@EnableSwagger2
//Servlet 、Filter、Listener  启动方式一， 启动时扫描Servlet 、Filter、Listener  组件 并实例化
@ServletComponentScan
@MapperScan("com.kunlun.erp.core.mapper")
@PropertySource(value = {"classpath:tomcat-cfg.properties","classpath:mybatis-cfg.properties"} )
public class CoreServerRun {
    private static final Logger logger = LoggerFactory.getLogger(CoreServerRun.class);
    public static void main(String[] args){
        ApplicationContext ctx= SpringApplication.run(CoreServerRun.class,args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        logger.info("bean count :{}", ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
//            logger.info("{},beanName:{}", ++i, str);
        }

    }




    /**
     * servlet 启动方式二
     * @return
     */
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean  servlet_bean  =new ServletRegistrationBean(new YourServlet());
        servlet_bean.addUrlMappings("/servlet/your/*");
        return servlet_bean;
    }
    /**
     * filter 启动方式二
     * @return
     */
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean filter_bean = new FilterRegistrationBean(new YourFilter());
        filter_bean.addUrlPatterns("/filter/your/1/*","/filter/your/2/*");
        return filter_bean;
    }
    /**
     * listener 启动方式二
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<YourListener> getServletListenerRegistrationBean(){
        ServletListenerRegistrationBean  listener_bean = new ServletListenerRegistrationBean<YourListener>(new YourListener());
        return listener_bean;
    }


}
