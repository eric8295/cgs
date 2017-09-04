/*
 * @(#) BaseInitializer.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package jscms.config.inittializer;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class BaseInitializer {
    /**
     * 配置文件
     */
    public static final String CMS_CONFIG_FILE = "jscms.properties";

    /**
     * hibernate 扫描路径
     */
    public static final String[] HIBERNATE_PACKAGES_TOSCAN = { "com.usky.cms.bean" };
}
