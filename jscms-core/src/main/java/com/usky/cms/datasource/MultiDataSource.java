/*
 * @(#) MultiDataSource.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class MultiDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    /*
     * @return
     * 
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return HOLDER.get();
    }

    /**
     * @param name
     */
    public static void setDataSourceName(String name) {
        HOLDER.set(name);
    }

    /**
     * 
     */
    public static void removeDataSourceName() {
        HOLDER.remove();
    }

}
