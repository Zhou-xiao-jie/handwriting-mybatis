package com.atxiaojie.mybatis.executor;

import com.atxiaojie.mybatis.configuration.Configuration;
import com.atxiaojie.mybatis.configuration.MappedStatement;
import com.atxiaojie.mybatis.util.ReflectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SimpleExecutor
 * @Description: SimpleExecutor
 * @author: zhouxiaojie
 * @date: 2021/11/3 23:52
 * @Version: V1.0.0
 */
public class SimpleExecutor implements Executor{

    private final Configuration configuration;

    public SimpleExecutor(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * @Description 封装SQL语句的那个MappedStatement对象，parameter是传入的sql参数
     * @Author zhouxiaojie
     * @Date 23:53 2021/11/3
     * @Param [mappedStatement, parameter]
     * @return java.util.List<E>
     **/
    public <E> List<E> query(MappedStatement mappedStatement, Object parameter) {
        List<E> result = new ArrayList<E>();
        /*try {
            Class.forName(configuration.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection(configuration.getJdbcUrl(),
                    configuration.getJdbcUsername(), configuration.getJdbcPassword());
            String redex = "#\\{([^}])*\\}";
            //将sql语句中的#{userId}替换为?
            String sql = mappedStatement.getSql().replaceAll(redex, "?");
            preparedStatement = conn.prepareStatement(sql);
            //处理占位符
            parametersize(preparedStatement, parameter);
            resultSet = preparedStatement.executeQuery();
            handlerResultSet(resultSet, result, mappedStatement.getResultType());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                resultSet.close();
                preparedStatement.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    private <E> void handlerResultSet(ResultSet resultSet, List<E> result, String resultType) {
        Class<E> clazz = null;
        //通过反射获取类对象
        try {
            clazz = (Class<E>) Class.forName(resultType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            while (resultSet.next()){
                Object entity = clazz.newInstance();
                //通过反射工具将resultSet中的数据填充到entity中
                ReflectionUtil.setPropToBeanFromResultSet(entity, resultSet);
                result.add((E) entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parametersize(PreparedStatement preparedStatement, Object parameter) throws SQLException {
        if(parameter instanceof Integer){
            preparedStatement.setInt(1,((Integer) parameter).intValue());
        }else if(parameter instanceof Long){
            preparedStatement.setLong(1,(Long) parameter);
        }else if(parameter instanceof String){
            preparedStatement.setString(1,(String) parameter);
        }
    }
}
