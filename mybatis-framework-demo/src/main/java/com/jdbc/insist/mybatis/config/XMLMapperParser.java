package com.jdbc.insist.mybatis.config;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;

/**
 * @ClassName: XMLMapperParser
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 19:11
 */
public class XMLMapperParser {

    private Configuration configuration;

    private String namespace;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * <mapper namespace="insist">
     *      <!-- select标签，封装了SQL语句信息、入参类型、结果映射类型 -->
     *      <select id="findUserById" parameterType="com.jdbc.insist.entity.User"
     *          resultType="com.jdbc.insist.entity.User" statementType="prepared">
     *          SELECT * FROM user WHERE id = #{id}
     *      </select>
     * </mapper>
     * @param element
     */
    public void parse(Element element) throws ClassNotFoundException {
        // 解析mapper
        namespace = element.attributeValue("namespace");
        // 解析 insert/delete/update/select
        List<Element> selects = element.elements("select");
        // 开始解析
        parseStatements(selects);

    }

    /**
     * <!-- select标签，封装了SQL语句信息、入参类型、结果映射类型 -->
     * <select id="findUserById" parameterType="com.jdbc.insist.entity.User"
     *     resultType="com.jdbc.insist.entity.User" statementType="prepared">
     *     SELECT * FROM user WHERE id = #{id}
     * </select>
     * @param elements
     * @throws ClassNotFoundException
     */
    private void parseStatements(List<Element> elements) throws ClassNotFoundException {
        for (Element element : elements) {
            parseStatement(element);
        }
    }

    private void parseStatement(Element element) throws ClassNotFoundException {
        // namespace + 配置文件中sql语句的id此例中id=findUserById
        String id = namespace +"."+ element.attributeValue("id");
        // 操作入参和结果集
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = getClassType(parameterType);
        // 查询结果返回类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = getClassType(resultType);
        // statement的类型
        String statementType = element.attributeValue("statementType");
        // 获取SQL语句 原生sql包含#{}
        String sqlText = element.getTextTrim();
        // 存放解析的sql语句
        SqlSource sqlSource = new SqlSource(sqlText);
        // 构建mappedStatement对象
        MappedStatement mappedStatement = MappedStatement.build()
                .buildId(id).buildParameterTypeClass(parameterTypeClass)
                .buildStatementType(statementType).buildResultTypeClass(resultTypeClass)
                .buildSqlSource(sqlSource);
        // 将mappedStatement对象放到configuration中
        configuration.addMappedStatements(id, mappedStatement);
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (null == parameterType || parameterType.equals("")) {
            return null;
        }
        Class<?> clazz = Class.forName(parameterType);
        return clazz;
    }
}
