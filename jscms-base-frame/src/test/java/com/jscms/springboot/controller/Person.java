package com.jscms.springboot.controller;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private int age;

    public Person() {
        super();
    }

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /** 
    * @return id 
    */
    public int getId() {
        return id;
    }

    /** 
    * @param id 要设置的 id 
    */
    public void setId(int id) {
        this.id = id;
    }
}
