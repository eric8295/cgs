/*
 * @(#) MemoryCacheEntity.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class MemoryCacheEntity<K, V> implements CacheEntity<K, V>, Serializable {

    /**
      * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
      */
    private static final long serialVersionUID = 2955715842928669104L;

    private int size;

    private String name;

    private LinkedHashMap<K, V> cacheMap = new LinkedHashMap<>(16, 0.75f, true);

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /*
     * @param name
     * 
     * @param size
     * 
     * @param prop
     * 
     * @see com.usky.cms.cache.CacheEntity#init(java.lang.String, java.lang.Integer, java.util.Properties)
     */
    @Override
    public void init(String name, Integer size, Properties prop) {
        if (null != size) {
            this.size = size;
        }
        this.name = name;
    }

    /*
     * @param key
     * 
     * @param value
     * 
     * @param expire
     * 
     * @see com.usky.cms.cache.CacheEntity#put(java.lang.Object, java.lang.Object, java.lang.Integer)
     */
    @Override
    public void put(K key, V value, Integer expire) {
        lock.writeLock().lock();
        try {
            cacheMap.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
      * clearCache(这里用一句话描述这个方法的作用)
      * @return
      */
    private List<V> clearCache() {
        List<V> list = null;
        if (size < cacheMap.size()) {
            Iterator<K> iterator = cacheMap.keySet().iterator();
            List<K> keyList = new ArrayList<K>();
            for (int i = 0; i < size / 2; i++) {
                keyList.add(iterator.next());
            }
            list = new ArrayList<>();
            for (K key : keyList) {
                list.add(cacheMap.remove(key));
            }
        }
        return list;
    }

    /*
     * @param key
     * 
     * @param value
     * 
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public List<V> put(K key, V value) {
        lock.writeLock().lock();
        try {
            cacheMap.put(key, value);
            return clearCache();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /*
     * @param key
     * 
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#remove(java.lang.Object)
     */
    @Override
    public V remove(K key) {
        lock.writeLock().lock();
        try {
            return cacheMap.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /*
     * @param key
     * 
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#contains(java.lang.Object)
     */
    @Override
    public boolean contains(K key) {
        lock.readLock().lock();
        try {
            return cacheMap.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    /*
     * @param key
     * 
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#get(java.lang.Object)
     */
    @Override
    public V get(K key) {
        lock.readLock().lock();
        try {
            return cacheMap.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    /*
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#clear()
     */
    @Override
    public List<V> clear() {
        lock.writeLock().lock();
        try {
            Collection<V> values = cacheMap.values();
            List<V> list = new ArrayList<>();
            if (!values.isEmpty()) {
                list.addAll(values);
            }
            cacheMap.clear();
            return list;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /*
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#getDataSize()
     */
    @Override
    public long getDataSize() {
        return cacheMap.size();
    }

    /*
     * @return
     * 
     * @see com.usky.cms.cache.CacheEntity#getAll()
     */
    @Override
    public Map<K, V> getAll() {
        return cacheMap;
    }

    /** 
    * @return size 
    */
    public int getSize() {
        return size;
    }

    /** 
    * @param size 要设置的 size 
    */
    public void setSize(int size) {
        this.size = size;
    }

}
