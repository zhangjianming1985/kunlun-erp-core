package com.kunlun.erp.core.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SubmitAspect
 * @Description 重复提交处理
 * @Author Jm.zhang
 * @Date 2020-01-01 13:02
 * @Version 1.0
 **/
@Aspect
@Configuration
public class SubmitAspect {
    private final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(100)
            // 设置缓存过期时间为S
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();



}
