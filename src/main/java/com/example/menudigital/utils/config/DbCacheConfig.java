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
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class DbCacheConfig {

    @Value("${spring.data.redis.url}")
    private String redisUrl;


    public static final String CACHE_NAME_PRODUCTOS="productos";
    public static final String CACHE_NAME_CATEGORIAS_PADRE="categoriaPadre";
    public static final String CACHE_NAME_CATEGORIA_HIJA="categoriaHija";

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
        Map<String, CacheConfig> configMap = new HashMap<>();
        configMap.put(CACHE_NAME_PRODUCTOS, new CacheConfig(60 * 1000,  60 * 1000)); // TTL de 1 minuto y tiempo máximo de inactividad de 8 horas
        configMap.put(CACHE_NAME_CATEGORIAS_PADRE, new CacheConfig(30 * 1000, 60 * 1000)); // TTL de 30 segundos y tiempo máximo de inactividad de 8 horas
        configMap.put(CACHE_NAME_CATEGORIA_HIJA,new CacheConfig(60* 1000,60 * 1000));
        return new RedissonSpringCacheManager(redissonClient,configMap);
    }
}
