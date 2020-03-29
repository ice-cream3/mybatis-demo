package com.jdbc.insist.mybatis.sqlsession;

import com.jdbc.insist.mybatis.config.Configuration;
import com.jdbc.insist.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @ClassName: DefaultSqlSession
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 17:28
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T selectOne(String statementId, Object params) {
        List<Object> selectList = selectList(statementId, params);
        if (null != selectList && selectList.size() == 1) {
            return (T) selectList.get(0);
        }
        return null;
    }

    public <T> List<T> selectList(String statementId, Object params) {
        // 执行器去执行查询,此处不应该每次都new一个执行器,应该创建缓存,待之后实现
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatements().get(statementId);
        return executor.query(configuration, mappedStatement, params);
    }
}
