package com.jdbc.insist.mybatis.config;

import java.util.List;

/**
 * @ClassName: BoundSql
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 16:40
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings;

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        super();
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMappings(ParameterMapping parameterMapping) {
        parameterMappings.add(parameterMapping);
    }
}
