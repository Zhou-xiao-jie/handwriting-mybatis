package com.atxiaojie.mybatis.sqlsession;

import com.atxiaojie.mybatis.bind.MapperProxy;
import com.atxiaojie.mybatis.configuration.Configuration;
import com.atxiaojie.mybatis.configuration.MappedStatement;
import com.atxiaojie.mybatis.executor.Executor;
import com.atxiaojie.mybatis.executor.SimpleExecutor;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @ClassName: DefaultSqlSession
 * @Description: DefaultSqlSession
 * @author: zhouxiaojie
 * @date: 2021/11/3 22:22
 * @Version: V1.0.0
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration){
        super();
        this.configuration = configuration;
        executor = new SimpleExecutor(configuration);
    }

    public <T> T selectOne(String statement, Object parameter) {
        List<T> selectList = this.selectList(statement, parameter);
        if(selectList == null || selectList.size() == 0){
            return null;
        }
        if(selectList.size() == 1){
            return (T) selectList.get(0);
        }else{
            throw new RuntimeException("too many result");
        }
    }

    public <T> List<T> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement().get(statement);
        //我们的查询方法最终还是交给了Executor去执行，Executor里面封装了JDBC操作。传入参数包含了SQL语句和SQL语句参数
        return executor.query(ms, parameter);
    }

    public <T> T getMapper(Class<T> type) {
        //通过动态代理生成了一个实现类，我们重点关注动态代理的实现，它是一个InvocationHandle
        //传入参数是this,就是sqlSesson的一个实例
        MapperProxy mapperProxy = new MapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, mapperProxy);
    }
}
