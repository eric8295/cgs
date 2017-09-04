/*
 * @(#) RootController.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.jscms.springboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@EnableAutoConfiguration
@RestController
public class RootController {
    public static final String PATH_ROOT = "/";

    @RequestMapping(PATH_ROOT)
    public String welcome() {
        return "hello world,Welcome!";
    }

    @RequestMapping("/{id}")
    public Person getPeople(@PathVariable Integer id) {
        Person people = new Person();
        people.setId(id);
        people.setAge(12);
        people.setName("lisi");
        return people;
    }
}
