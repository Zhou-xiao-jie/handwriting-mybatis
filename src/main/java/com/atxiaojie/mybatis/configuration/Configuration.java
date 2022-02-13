package com.atxiaojie.mybatis.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Configuration
 * @Description:
 * @author: zhouxiaojie
 * @date: 2021/11/3 0:19
 * @Version: V1.0.0
 */
public class Configuration {

    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcPassword;
    private String jdbcUsername;
    private Map<String, MappedStatement> mappedStatement = new HashMap<String, MappedStatement>();

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public Map<String, MappedStatement> getMappedStatement() {
        return mappedStatement;
    }

    public void setMappedStatement(Map<String, MappedStatement> mappedStatement) {
        this.mappedStatement = mappedStatement;
    }
}
