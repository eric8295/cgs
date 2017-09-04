/*
 * @(#) UsernamePasswordAuthenticator.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.proxy;

import java.net.PasswordAuthentication;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
public class UsernamePasswordAuthenticator extends java.net.Authenticator {
    private PasswordAuthentication auth;

    public UsernamePasswordAuthenticator(String username, String password) {
        auth = new PasswordAuthentication(username, password.toCharArray());
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return auth;
    }
}
