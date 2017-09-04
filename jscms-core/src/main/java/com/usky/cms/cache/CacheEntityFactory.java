/*
 * @(#) CacheEntityFactory.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.cache;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import lombok.extern.slf4j.Slf4j;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@Slf4j
public class CacheEntityFactory {
    public static final String MEMORY_CACHE_ENTITY = "cms_memory";

    private String defaultCacheEntity;

    private int defaultSize = 100;

    private Properties properties;

    public CacheEntityFactory(String configurationResourceName) throws IOException {
        this.properties = PropertiesLoaderUtils.loadAllProperties(configurationResourceName);

        try {
            setDefaultSize(Integer.parseInt(properties.getProperty("cache.defaultSize", "100")));
        } catch (Exception e) {
            setDefaultSize(defaultSize);
            log.error(e.getMessage());
        }
    }

    public <K, V> CacheEntity<K, V> createCacheEntity(String name, String type)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        int size = defaultSize;

        try {
            size = Integer.valueOf(properties.getProperty("cache.size.") + name);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        CacheEntity<K, V> cacheEntity;
        if (MEMORY_CACHE_ENTITY.equals(type)) {
            cacheEntity = new MemoryCacheEntity<>();
        } else {
            @SuppressWarnings("unchecked")
            Class<CacheEntity<K, V>> clazz = (Class<CacheEntity<K, V>>) Class.forName(type);
            cacheEntity = clazz.newInstance();
        }
        cacheEntity.init(name, size, properties);
        return cacheEntity;
    }

    public <K, V> CacheEntity<K, V> createCacheEntity(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return createCacheEntity(name, getDefaultCacheEntity());
    }

    public synchronized String getDefaultCacheEntity() {
        if (null == defaultCacheEntity) {
            defaultCacheEntity = properties.getProperty("cache.type");
        }
        return defaultCacheEntity;
    }

    /** 
    * @return defaultSize 
    */
    public int getDefaultSize() {
        return defaultSize;
    }

    /** 
    * @param defaultSize 要设置的 defaultSize 
    */
    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }

}
