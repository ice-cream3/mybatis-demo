package com.jdbc.insist.mybatis.config;

import com.jdbc.insist.mybatis.sqlsession.DocumentReader;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName: XMLConfiguration
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 18:11
 */
public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser() {}
    public XMLConfigParser(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    public Configuration parserConfiguration(Element rootElement) {
        parserEnvironments(rootElement.element("environments"));

        parserMappers(rootElement.element("mappers"));

        return configuration;
    }

    /**
     * <!-- mybatis 数据源环境配置 -->
     * 	<environments default="dev">
     * 		<environment id="dev">
     * 			<!-- 配置数据源信息 -->
     * 			<dataSource type="DBCP">
     * 				<property name="driver" value="com.mysql.jdbc.Driver"></property>
     * 				<property name="url"
     * 					value="jdbc:mysql://localhost:3306/jdbc_demo"></property>
     * 				<property name="username" value="root"></property>
     * 				<property name="password" value="root"></property>
     * 			</dataSource>
     * 		</environment>
     * 	</environments>
     * @param element
     * @return
     */
    private void parserEnvironments(Element element) {
        // <environments default="dev">
        String defaultId = element.attributeValue("default");
        List<Element> elements = element.elements("environment");

        for (Element envElement : elements) {
            String envId = envElement.attributeValue("id");
            // 如果和默认的环境ID匹配，才进行解析
            if (envId != null && envId.equals(defaultId)) {
                parseDataSource(envElement.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element element) {
        String type = element.attributeValue("type");
        if (type == null || type.equals("")) {
            type = "DBCP";
        }
        List<Element> elements = element.elements("property");

        Properties properties = new Properties();
        for (Element propertyEle : elements) {
            String name = propertyEle.attributeValue("name");
            String value = propertyEle.attributeValue("value");
            properties.setProperty(name, value);
        }

        BasicDataSource dataSource = null;
        if (type.equals("DBCP")) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        }
        configuration.setDataSource(dataSource);
    }

    /**
     * <!-- 映射文件加载 -->
     * 	<mappers>
     * 		<!-- resource指定映射文件的类路径 -->
     * 		<mapper resource="mapper/UserMapper.xml"></mapper>
     * 		<mapper resource="mapper/UserMapper.xml"></mapper>
     * 		<mapper resource="mapper/UserMapper.xml"></mapper>
     * 	</mappers>
     * @param element
     */
    private void parserMappers(Element element) {
        List<Element> elements = element.elements("mapper");
        for (Element mapperEle : elements) {
            parseMapper(mapperEle);
        }
    }

    private void parseMapper(Element mapperEle) {
        String resource = mapperEle.attributeValue("resource");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        Document document = DocumentReader.createDocument(inputStream);
        XMLMapperParser xmlMapperParser = new XMLMapperParser(configuration);
        xmlMapperParser.parse(document.getRootElement());
    }
}
