package com.jdbc.insist.mybatis.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Configration
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 17:51
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void addMappedStatements(String statementId, MappedStatement mappedStatement) {
        mappedStatements.put(statementId, mappedStatement);
    }
}
