package com.ctrip.www.android;

import java.io.Serializable;

/**
 * Created by huangchengdu on 16/8/16.
 */
public class MyData implements Serializable {
    private String name;
    private int age;

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

    @Override
    public String toString() {
        return "MyData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public MyData(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
