/*
 * @(#) AbstractFreemarkerView.java
 * @Author:cgs(mail) 2017年8月29日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.base;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.usky.cms.component.SiteComponent;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月29日
  * @version 1.0
  * @Function 类功能说明
  */
public abstract class AbstractFreemarkerView extends FreeMarkerView {
    /**
     * Site Component
     */
    public static SiteComponent siteComponent;
}
