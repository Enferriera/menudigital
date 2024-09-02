package com.example.menudigital.utils.config;

import org.apache.logging.log4j.status.StatusLogger;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableCaching
public class DbCacheConfig {

    @Value("${spring.data.redis.url}")
    private String redisUrl;


    public static final String CACHE_NAME="productos";

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        var config= new Config();
        config.useSingleServer()
                .setAddress(redisUrl)
                ;
        return Redisson.create(config);
    }

    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient){
        var config= Collections.singletonMap(CACHE_NAME,new CacheConfig());
        return new RedissonSpringCacheManager(redissonClient,config);
    }
}
