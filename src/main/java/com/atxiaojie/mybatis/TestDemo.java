package com.atxiaojie.mybatis;

import com.atxiaojie.mybatis.entity.User;
import com.atxiaojie.mybatis.factory.DefaultSqlSessionFactory;
import com.atxiaojie.mybatis.mapper.UserMapper;
import com.atxiaojie.mybatis.sqlsession.SqlSession;

/**
 * @ClassName: TestDemo
 * @Description: 测试手写mybatis框架
 * @author: zhouxiaojie
 * @date: 2021/11/3 0:17
 * @Version: V1.0.0
 */
public class TestDemo {

    public static void main(String[] args) {
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(1);
        System.out.println(user);
    }
}
