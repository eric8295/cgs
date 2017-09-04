/*
 * @(#) SiteComponent.java
 * @Author:cgs(mail) 2017年8月29日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.usky.cms.api.Cache;
import com.usky.cms.common.base.BaseConfig;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月29日
  * @version 1.0
  * @Function 类功能说明
  */
public class SiteComponent implements Cache, BaseConfig {

    private String rootPath;
    private String webFilePath;
    private String taskTemplateFilePath;
    private String webTemplateFilePath;

    private int defaultSiteId;
    private Set<Integer> idSet = new HashSet<Integer>();

    /*
     * @see com.usky.cms.api.Cache#clear()
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    public void setSiteMasters(String siteMasters) {
        String[] masters = StringUtils.split(siteMasters, COMMA_DELIMITED);
        for (String master : masters) {
            Integer id;
            try {
                id = Integer.parseInt(master);
            } catch (NumberFormatException e) {
                id = null;
            }
            // if (StringUtils.notEmpty(id)) {
            // idSet.add(id);
            // }
        }
    }

    /** 
    * @return rootPath 
    */
    public String getRootPath() {
        return rootPath;
    }

    /** 
    * @param rootPath 要设置的 rootPath 
    */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    /** 
    * @return defaultSiteId 
    */
    public int getDefaultSiteId() {
        return defaultSiteId;
    }

    /** 
    * @param defaultSiteId 要设置的 defaultSiteId 
    */
    public void setDefaultSiteId(int defaultSiteId) {
        this.defaultSiteId = defaultSiteId;
    }
}
