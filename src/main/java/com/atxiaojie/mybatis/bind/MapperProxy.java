package com.atxiaojie.mybatis.bind;

import com.atxiaojie.mybatis.sqlsession.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @ClassName: MapperProxy
 * @Description: Mapper代理对象
 * @author: zhouxiaojie
 * @date: 2021/11/3 0:18
 * @Version: V1.0.0
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //最终还是将执行方法传给sqlSession，因为sqlSession里面封装了Executor
        //根据调用方法的类名和方法名以及参数，传给sqlSession对应的方法
        if(Collection.class.isAssignableFrom(method.getReturnType())){
            return sqlSession.selectList(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
        }else{
            return sqlSession.selectOne(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
        }
    }
}
