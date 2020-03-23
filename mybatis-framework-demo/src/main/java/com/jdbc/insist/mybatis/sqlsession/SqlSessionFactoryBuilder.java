package com.jdbc.insist.mybatis.sqlsession;

import com.jdbc.insist.mybatis.config.Configuration;
import com.jdbc.insist.mybatis.config.XMLConfigParser;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * @ClassName: SqlSessionFactoryBuilder
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 17:45
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    public SqlSessionFactoryBuilder() {
        super();
        this.configuration = new Configuration();
    }

    public SqlSessionFactory build(InputStream inputStream) {
        // 解析inputStream
        Document document = DocumentReader.createDocument(inputStream);
        configuration = new XMLConfigParser(configuration).parserConfiguration(document.getRootElement());
        return build();
    }

    public SqlSessionFactory build(Reader reader) {
        return build();
    }

    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
