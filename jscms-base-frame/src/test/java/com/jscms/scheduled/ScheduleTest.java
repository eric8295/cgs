/*
 * @(#) ScheduleTest.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.jscms.scheduled;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@Configuration
@Import(TaskScheduleConfig.class)
public class ScheduleTest {

    @Test
    public void testSchedule() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskScheduleConfig.class);
        while (true) {
            Thread.sleep(3000);
            System.out.println("----" + Thread.currentThread().getName());
        }
    }
}
