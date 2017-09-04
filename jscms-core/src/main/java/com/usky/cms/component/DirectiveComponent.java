/*
 * @(#) DirectiveComponent.java
 * @Author:cgs(mail) 2017年8月29日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.component;

import com.usky.cms.common.base.BaseConfig;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月29日
  * @version 1.0
  * @Function 类功能说明
  */
public class DirectiveComponent implements BaseConfig {
    private String directiveRemoveRegex;
    private String methodRemoveRegex;

    /** 
    * @return directiveRemoveRegex 
    */
    public String getDirectiveRemoveRegex() {
        return directiveRemoveRegex;
    }

    /** 
    * @param directiveRemoveRegex 要设置的 directiveRemoveRegex 
    */
    public void setDirectiveRemoveRegex(String directiveRemoveRegex) {
        this.directiveRemoveRegex = directiveRemoveRegex;
    }

    /** 
    * @return methodRemoveRegex 
    */
    public String getMethodRemoveRegex() {
        return methodRemoveRegex;
    }

    /** 
    * @param methodRemoveRegex 要设置的 methodRemoveRegex 
    */
    public void setMethodRemoveRegex(String methodRemoveRegex) {
        this.methodRemoveRegex = methodRemoveRegex;
    }
}
