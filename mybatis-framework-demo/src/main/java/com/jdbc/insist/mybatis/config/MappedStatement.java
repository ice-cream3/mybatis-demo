package com.jdbc.insist.mybatis.config;

/**
 * @ClassName: MappedStatement
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 16:33
 */
public class MappedStatement {

    private String id;
    private Class<?> parameterTypeClass;
    private Class<?> resultTypeClass;
    private String statementType;
    private SqlSource sqlSource;

    public static MappedStatement build() {
        return new MappedStatement();
    }

    public String getId() {
        return id;
    }

    public MappedStatement buildId(String id) {
        this.id = id;
        return this;
    }

    public Class<?> getParameterTypeClass() {
        return parameterTypeClass;
    }

    public MappedStatement buildParameterTypeClass(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
        return this;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }

    public MappedStatement buildResultTypeClass(Class<?> resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
        return this;
    }

    public String getStatementType() {
        return statementType;
    }

    public MappedStatement buildStatementType(String statementType) {
        this.statementType = statementType;
        return this;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public MappedStatement buildSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParameterTypeClass(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public void setResultTypeClass(Class<?> resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}
