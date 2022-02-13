package com.atxiaojie.mybatis.entity;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: User实体类
 * @author: zhouxiaojie
 * @date: 2021/11/3 21:18
 * @Version: V1.0.0
 */
public class User {

    private Integer id;
    private String userName;
    private String passWord;
    private Integer age;
    private Date regTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                ", regTime=" + regTime +
                '}';
    }
}
