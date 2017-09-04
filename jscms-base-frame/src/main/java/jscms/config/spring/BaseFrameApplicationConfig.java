/*
 * @(#) BaseFrameApplicationConfig.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package jscms.config.spring;

import static jscms.config.inittializer.BaseInitializer.CMS_CONFIG_FILE;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.usky.cms.base.AbstractFreemarkerView;
import com.usky.cms.cache.CacheEntityFactory;
import com.usky.cms.common.constants.CmsSysConstants;
import com.usky.cms.common.search.MultiTokenizerFactory;
import com.usky.cms.component.DirectiveComponent;
import com.usky.cms.component.SiteComponent;
import com.usky.cms.component.TemplateComponent;
import com.usky.cms.datasource.BasicDataSource;

import jscms.config.inittializer.BaseInitializer;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@Configuration
@ComponentScans(value = { @ComponentScan(basePackages = { "org.jscms" }, excludeFilters = { @ComponentScan.Filter(value = { Controller.class }) }) })
@MapperScan(basePackages = "org.jscms.biz.mapper")
@PropertySource({ "classpath:" + CMS_CONFIG_FILE })
@EnableTransactionManagement
@EnableScheduling// 支持spring注解开启对计划任务的支持
public class BaseFrameApplicationConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource initDataSource() {
        BasicDataSource dataSource = new BasicDataSource(getDirPath("") + BasicDataSource.DATABASE_CONFIG_FILENAME);
        try {
            BasicDataSource.initDefautlDataSource();
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 
      * hibernateTransactionManager(设置hibernate事务)
      * @param sessionFactory
      * @return
     */
    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    /**
     * 
      * mybatisSqlSessionFactoryBean(mybatis session factory)
      * @param dataSource
      * @return
      * @throws IOException
     */
    @Bean
    public SqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(true);
        configuration.setLazyLoadingEnabled(false);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**/*mapper.xml"));
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean;
    }

    /**
     * hibernate session factory
      * hibernateSessionFactory(这里用一句话描述这个方法的作用)
      * @param dataSource
      * @return
      * @throws IOException
     */
    @Bean
    public FactoryBean<SessionFactory> hibernateSessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(BaseInitializer.HIBERNATE_PACKAGES_TOSCAN);
        MultiTokenizerFactory.setName(env.getProperty("cms.tokenizerFactory"));
        Properties properties = PropertiesLoaderUtils.loadAllProperties(env.getProperty("cms.hibernate.configFilePath"));
        // hibernate search index path
        properties.setProperty("hibernate.search.default.indexBase", getDirPath("/indexes/"));
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    /**
     * 缓存工厂
     *
     * Cache Factory
     *
     * @return
     * @throws IOException
     */
    @Bean
    public CacheEntityFactory cacheEntityFactory() throws IOException {
        CacheEntityFactory cacheBean = new CacheEntityFactory(env.getProperty("cms.cache.configFilePath"));
        return cacheBean;
    }

    /**
     * 国际化
      * messageSource(这里用一句话描述这个方法的作用)
      * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(new String[] { "language.message", "language.config", "language.operate" });
        source.setCacheSeconds(300);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    /**
     * 指令组件
      * (这里用一句话描述这个方法的作用)
      * @param path
      * @return
     */
    @Bean
    public DirectiveComponent directiveComponent() {
        DirectiveComponent dc = new DirectiveComponent();
        dc.setDirectiveRemoveRegex(env.getProperty("cms.directiveRemoveRegex"));
        dc.setMethodRemoveRegex(env.getProperty("cms.methodRemoveRegex"));
        return dc;
    }

    /**
     * 
      * templateComponent(模板组件)
      * @return
     */
    public TemplateComponent templateComponent() {
        TemplateComponent template = new TemplateComponent();
        template.setDirectivePrefix(env.getProperty("cms.directivePrefix"));
        return template;
    }

    @Bean
    public SiteComponent siteComponent() {
        SiteComponent site = new SiteComponent();
        site.setRootPath(getDirPath(""));
        site.setSiteMasters(env.getProperty("cms.masterSiteIds"));
        site.setDefaultSiteId(Integer.parseInt(env.getProperty("cms.defaultSiteId")));
        return AbstractFreemarkerView.siteComponent = site;
    }

    private String getDirPath(String path) {
        String filePath = CmsSysConstants.CMS_FILEPATH + path;
        File dir = new File(filePath);
        dir.mkdirs();
        return dir.getAbsolutePath();
    }
}
