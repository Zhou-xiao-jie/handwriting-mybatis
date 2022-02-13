package com.atxiaojie.mybatis.factory;

import com.atxiaojie.mybatis.sqlsession.SqlSession;

public interface SqlSessionFactory {

    SqlSession openSession();
}
