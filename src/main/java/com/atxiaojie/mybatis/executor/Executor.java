package com.atxiaojie.mybatis.executor;

import com.atxiaojie.mybatis.configuration.MappedStatement;

import java.util.List;

/**
 * @Description Mybatis核心接口之一，定义了数据库操作的基本方法
 * JDBC,sqlSession的所有功能都是基于它来实现的
 * @Author zhouxiaojie
 * @Date 23:45 2021/11/3
 * @Param
 * @return
 **/
public interface Executor {

    /**
     * @Description 封装SQL语句的那个MappedStatement对象，parameter传入SQL参数
     * <E>将数据对象转换成指定对象结果返回
     * @Author zhouxiaojie
     * @Date 23:48 2021/11/3
     * @Param [mappedStatement, parameter]
     * @return java.util.List<E>
     **/
    <E> List<E> query(MappedStatement mappedStatement, Object parameter);
}
