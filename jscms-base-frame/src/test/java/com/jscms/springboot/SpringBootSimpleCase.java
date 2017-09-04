/*
 * @(#) SpringBootSimpleCase.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.jscms.springboot;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@SpringBootApplication
public class SpringBootSimpleCase implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleCase.class, args);
    }

    /*
     * @param arg0
     * 
     * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.
     * ConfigurableEmbeddedServletContainer)
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8088);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8088);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
        return factory;
    }
}
