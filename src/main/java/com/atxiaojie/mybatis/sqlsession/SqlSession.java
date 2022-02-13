package com.atxiaojie.mybatis.sqlsession;

import java.util.List;

/**
 * @Description 封装所有数据库的操作，所有功能都是基于Executor来实现的，Executor封装了JDBC操作
 * @Author zhouxiaojie
 * @Date 23:07 2021/11/3
 * @Param
 * @return
 **/
public interface SqlSession {

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement, Object parameter);

    <T> T getMapper(Class<T> type);
}
