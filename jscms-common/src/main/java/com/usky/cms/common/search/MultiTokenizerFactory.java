/*
 * @(#) TokenizerFactory.java
 * @Author:cgs(mail) 2017年8月28日
 * @Copyright (c) 2002-2017 usky.com Limited. All rights reserved.
 */
package com.usky.cms.common.search;

import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import lombok.extern.slf4j.Slf4j;

/**
  * @author cgs(chengs@usky.com.cn) 2017年8月28日
  * @version 1.0
  * @Function 类功能说明
  */
@Slf4j
public class MultiTokenizerFactory extends TokenizerFactory {

    private static String name;

    public TokenizerFactory tokenizerFactory;

    /*
     * @param arg0
     * 
     * @return
     * 
     * @see org.apache.lucene.analysis.util.TokenizerFactory#create(org.apache.lucene.util.AttributeFactory)
     */
    @Override
    public Tokenizer create(AttributeFactory factory) {
        return tokenizerFactory.create(factory);
    }

    /**
     * @param args
     */
    public MultiTokenizerFactory(Map<String, String> args) {
        this(args, name);
    }

    /**
      * 创建一个新的实例 
      * @param args
      * @param name2
      */
    public MultiTokenizerFactory(Map<String, String> args, String name) {
        super(args);

        Set<String> set = availableTokenizers();
        if (!set.contains(name)) {
            name = "standard";
        }
        tokenizerFactory = forName(name, args);
        log.info(new StringBuilder().append(name).append(" tokenizer factory created,available tokenizers:").append(set).toString());
        if (!args.isEmpty()) {
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown parameters: ").append(args).toString());
        }
    }

    /** 
    * @param name 要设置的 name 
    */
    public static void setName(String name) {
        MultiTokenizerFactory.name = name;
    }
}
