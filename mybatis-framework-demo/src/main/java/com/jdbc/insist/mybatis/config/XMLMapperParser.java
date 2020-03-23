package com.jdbc.insist.mybatis.config;

import org.dom4j.Element;

/**
 * @ClassName: XMLMapperParser
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 19:11
 */
public class XMLMapperParser {

    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * <mapper namespace="insist">
     * 	<!-- select标签，封装了SQL语句信息、入参类型、结果映射类型 -->
     * 	<select id="findUserById" parameterType="com.jdbc.insist.entity.User"
     * 		resultType="com.jdbc.insist.entity.User" statementType="prepared">
     * 		SELECT * FROM user WHERE id = #{id}
     * 	</select>
     * </mapper>
     * @param element
     */
    public void parse(Element element) {
        // 解析mapper
        // 解析 insert/delete/update/select
        // 操作入参和结果集
        // 获取SQL语句,封装到dataSources对象中,因为此时还没有参数值,需要在执行时动态转换
    }
}
