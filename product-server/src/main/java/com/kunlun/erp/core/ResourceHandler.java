package com.kunlun.erp.core;

import com.kunlun.erp.core.common.configuration.FileProperties;
import com.kunlun.erp.core.interceptor.CorsHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @ClassName ResourceHandler
 * @Description 资源访问配置
 * @Author Jm.zhang
 * @Date 2019/12/6 17:58
 * @Version 1.0
 **/
@Configuration
public class ResourceHandler extends WebMvcConfigurationSupport {
    @Resource
    private FileProperties file_properties;
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler(file_properties.getPic_access_url())
                .addResourceLocations("file:"+file_properties.getPic_folder_path());

        registry.addResourceHandler(file_properties.getDoc_access_url())
                .addResourceLocations("file:"+file_properties.getDoc_folder_path());
    }


    @Resource
    private CorsHandlerInterceptor cors_interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //指定拦截器类
        InterceptorRegistration registration = registry.addInterceptor(cors_interceptor);
        registration.addPathPatterns("/api/v1/**");
//        .excludePathPatterns("/**/query_token/**");

    }
}
