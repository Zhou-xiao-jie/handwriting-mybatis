package com.atxiaojie.mybatis.util;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: ReflectionUtil
 * @Description: ReflectionUtil
 * @author: zhouxiaojie
 * @date: 2021/11/4 0:16
 * @Version: V1.0.0
 */
public class ReflectionUtil {

    public static void setPropToBeanFromResultSet(Object entity, ResultSet resultSet) throws SQLException {
        //通过反射方法获取对象的所有字段
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            String fieldName = declaredField.getName();
            if(declaredField.getType().getSimpleName().equals("String")){
                setPropToBean(entity, fieldName, resultSet.getString(StrUtil.toUnderlineCase(fieldName)));
            }else if(declaredField.getType().getSimpleName().equals("Integer")){
                setPropToBean(entity, fieldName, resultSet.getInt(StrUtil.toUnderlineCase(fieldName)));
            }else if(declaredField.getType().getSimpleName().equals("Long")){
                setPropToBean(entity, fieldName, resultSet.getLong(StrUtil.toUnderlineCase(fieldName)));
            }else if(declaredField.getType().getSimpleName().equals("int")){
                setPropToBean(entity, fieldName, resultSet.getInt(StrUtil.toUnderlineCase(fieldName)));
            }else if(declaredField.getType().getSimpleName().equals("Date")){
                setPropToBean(entity, fieldName, resultSet.getDate(fieldName));
            }
        }
    }

    private static void setPropToBean(Object bean, String name, Object value){
        Field f;
        try {
            f = bean.getClass().getDeclaredField(name);
            f.setAccessible(true);
            f.set(bean, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

}
