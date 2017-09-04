/*
 * @(#) TaskScheduleConfig.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.jscms.scheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@Configuration
@ComponentScan("com.jscms.scheduled")
@EnableScheduling // 通过@EnableScheduling注解开启对计划任务的支持
public class TaskScheduleConfig {

}
