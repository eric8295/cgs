/*
 * @(#) AppInitInitializer.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package jscms.config.inittializer;

import static com.usky.cms.common.constants.CmsSysConstants.CMS_FILEPATH;
import static com.usky.cms.common.constants.CmsSysConstants.TEMP_CMS_FILEPATH;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.WebApplicationInitializer;

import com.usky.cms.common.base.BaseConfig;
import com.usky.cms.proxy.UsernamePasswordAuthenticator;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class AppInitInitializer extends BaseInitializer implements WebApplicationInitializer, BaseConfig {

    protected final Logger logger = LoggerFactory.getLogger(AppInitInitializer.class);

    /**
     * 初始化数据目录
     * @param arg0
     * 
     * @throws ServletException
     * 
     * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
     */
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        Properties config = null;
        try {
            config = PropertiesLoaderUtils.loadAllProperties(CMS_CONFIG_FILE);
            checkFilePath(context, config.getProperty("cms.filePath"));
            if (StringUtils.equals("true", config.getProperty("cms.proxy.enable", "false"))) {
                Properties proxyProperties = PropertiesLoaderUtils.loadAllProperties("jscms.proxy.configFilePath");
                System.setProperties(proxyProperties);
                Authenticator.setDefault(
                        new UsernamePasswordAuthenticator(config.getProperty("cms.proxy.username"), config.getProperty("cms.proxy.password")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
      * checkFilePath(这里用一句话描述这个方法的作用)
      * @param context
      * @param property
      */
    private void checkFilePath(ServletContext context, String defaultPath) {
        CMS_FILEPATH = System.getProperty("cms.filePath", defaultPath);

        boolean exist = false;
        try {
            File rootDir = new File(CMS_FILEPATH);
            exist = rootDir.exists();
            if (!exist) {
                rootDir.mkdirs();
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!exist) {
            // 将目录设置为项目所在目录
            CMS_FILEPATH = new File(context.getRealPath("/"), TEMP_CMS_FILEPATH).getPath();
        }
    }

}
