package com.atxiaojie.mybatis.mapper;

import com.atxiaojie.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

    public User selectById(int id);

    public List<User> selectAll();
}
