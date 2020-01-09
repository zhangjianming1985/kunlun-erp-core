package com.kunlun.erp.core.common.cache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @ClassName EhCacheConfig
 * @Description EhCache配置
 * @Author Jm.zhang
 * @Date 2019/11/21 17:18
 * @Version 1.0
 **/
@Configuration
public class EhCacheConfig {
    /**
     * preConfigured：缓存的名称，可以创建多个，可以对每个缓存进行不同的设置。比方说对A缓存设置过期时间，B缓存不设置缓存名称。
     * heap(1000L)：代表preConfigured下的缓存最多存1000个key，如果是1001则把第一个缓存删除。当然也可以设置offHeap()这个代表如果超过heap()设置的缓存数，则把缓存存到offHeap()设置的磁盘上。（本次没用）
     * withExpiry：对preConfigured下的缓存设置超时时间（上面的例子是20秒自动过期），EhCache的缓存过期机制有3种
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(CacheKeyConstants.user_cache,CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class,ResourcePoolsBuilder.heap(1000L)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(3600))))
                .withCache(CacheKeyConstants.full_area_data,CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class,ResourcePoolsBuilder.heap(1000L)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(30))))
                .build();
        cacheManager.init();
        return cacheManager;
    }

}
