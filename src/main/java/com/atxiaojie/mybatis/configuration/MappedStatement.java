package com.atxiaojie.mybatis.configuration;

/**
 * @ClassName: MappedStatement
 * @Description: 保存XML中的配置以及SQL的对象
 * @author: zhouxiaojie
 * @date: 2021/11/3 0:20
 * @Version: V1.0.0
 */
public class MappedStatement {

    private String namespace;
    private String id;
    private String resultType;
    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
