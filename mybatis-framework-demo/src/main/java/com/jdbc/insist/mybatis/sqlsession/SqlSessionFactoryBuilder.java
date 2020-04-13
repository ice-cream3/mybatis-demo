package com.jdbc.insist.mybatis.sqlsession;

import com.jdbc.insist.mybatis.config.Configuration;
import com.jdbc.insist.mybatis.config.XMLConfigParser;
import com.jdbc.insist.mybatis.utils.DocumentReader;
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

    /**
     * build方法解析全局配置文件,放到configuration对象中
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) {
        // 通过dom4j的SAXReader读取inputStream获取document对象
        Document document = DocumentReader.createDocument(inputStream);
        // 解析配置文件获取dataSource和mapperStatement放入configuration中
        configuration = new XMLConfigParser(configuration).parserConfiguration(document.getRootElement());
        return build();
    }

    public SqlSessionFactory build(Reader reader) {
        return build();
    }

    /**
     * sqlSessionFactory接口的实现类型分很多种.此处以默认实现返回
     * @return
     */
    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
