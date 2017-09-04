/*
 * @(#) CmsDataSource.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.datasource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;
import static java.lang.Integer.parseInt;

import java.beans.PropertyVetoException;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class BasicDataSource extends MultiDataSource {
    public static final String DATABASE_CONFIG_FILENAME = "/database.properties";
    public static final String DATABASE_CONFIG_TEMPLATE = "config/database-template.properties";

    private static BasicDataSource basicDataSource;
    private String dbconfigFilePath;

    private Map<Object, Object> dataSources = new HashMap<Object, Object>();

    public BasicDataSource(String filePath) {
        this.dbconfigFilePath = filePath;
        basicDataSource = this;
    }

    public static void initDefautlDataSource() throws IOException, PropertyVetoException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(basicDataSource.dbconfigFilePath);
        DataSource dataSource = initDataSource(properties);

        basicDataSource.getDataSources().put("default", dataSource);
        basicDataSource.setTargetDataSources(basicDataSource.getDataSources());
        basicDataSource.setDefaultTargetDataSource(dataSource);
        basicDataSource.init();
    }

    /**
      * initDataSource(这里用一句话描述这个方法的作用)
      * @param properties
      * @return
     * @throws PropertyVetoException 
      */
    private static DataSource initDataSource(Properties properties) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
        dataSource.setUser(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setAutoCommitOnClose(Boolean.parseBoolean(properties.getProperty("cpool.autoCommitOnClose")));
        dataSource.setCheckoutTimeout(parseInt(properties.getProperty("cpool.checkoutTimeout")));
        dataSource.setInitialPoolSize(parseInt(properties.getProperty("cpool.minPoolSize")));
        dataSource.setMinPoolSize(parseInt(properties.getProperty("cpool.minPoolSize")));
        dataSource.setMaxPoolSize(parseInt(properties.getProperty("cpool.maxPoolSize")));
        dataSource.setMaxIdleTime(parseInt(properties.getProperty("cpool.maxIdleTime")));
        dataSource.setAcquireIncrement(parseInt(properties.getProperty("cpool.acquireIncrement")));
        dataSource.setMaxIdleTimeExcessConnections(parseInt(properties.getProperty("cpool.maxIdleTimeExcessConnections")));
        return dataSource;
    }

    @Override
    public void afterPropertiesSet() {

    }

    /**
     * 
     */
    public void init() {
        super.afterPropertiesSet();
    }

    /**
     * @param name
     * @param dataSource
     */
    public void put(String name, Object dataSource) {
        dataSources.put(name, dataSource);
    }

    /**
     * @return
     */
    public Map<Object, Object> getDataSources() {
        return dataSources;
    }
}
