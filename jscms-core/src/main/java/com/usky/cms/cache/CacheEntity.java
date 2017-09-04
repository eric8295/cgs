/*
 * @(#) CacheEntity.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.cache;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public interface CacheEntity<K, V> {

    public void init(String name, Integer size, Properties prop);

    public void put(K key, V value, Integer expire);

    public List<V> put(K key, V value);

    public V remove(K key);

    public boolean contains(K key);

    public V get(K key);

    public List<V> clear();

    public long getDataSize();

    public Map<K, V> getAll();
}
