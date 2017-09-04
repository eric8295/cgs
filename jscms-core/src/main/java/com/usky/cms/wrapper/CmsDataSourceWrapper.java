/*
 * @(#) CmsDataSourceWrapper.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.wrapper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usky.cms.common.utils.EncryptUtils;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class CmsDataSourceWrapper extends BasicDataSource {
    final private Logger logger = LoggerFactory.getLogger(CmsDataSourceWrapper.class);

    @Override
    final public void setPassword(String password) {
        try {
            EncryptUtils des = new EncryptUtils("jscms");
            password = des.decrypt(password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        super.setPassword(password);
    }
}
