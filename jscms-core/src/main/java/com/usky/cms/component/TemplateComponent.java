/*
 * @(#) TemplateComponent.java
 * @Author:cgs(mail) 2017年8月29日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.component;

import com.usky.cms.common.base.BaseConfig;

import lombok.extern.slf4j.Slf4j;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月29日
  * @version 1.0
  * @Function 类功能说明
  */
@Slf4j
public class TemplateComponent implements BaseConfig {

    private String directivePrefix;

    /** 
    * @return directivePrefix 
    */
    public String getDirectivePrefix() {
        return directivePrefix;
    }

    /** 
    * @param directivePrefix 要设置的 directivePrefix 
    */
    public void setDirectivePrefix(String directivePrefix) {
        this.directivePrefix = directivePrefix;
    }
}
