package com.jdbc.insist.mybatis.sqlsession;

import com.jdbc.insist.mybatis.config.Configuration;

/**
 * @ClassName: DefaultSqlSessionFactory
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 17:46
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        // defaultSqlSessionFactory实现的只是接口,接口要有相应的实现类DefaultSqlSession,DefaultSqlSession去实现sqlSession
        return new DefaultSqlSession(configuration);
    }
}
