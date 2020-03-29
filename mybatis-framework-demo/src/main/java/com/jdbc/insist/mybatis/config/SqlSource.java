package com.jdbc.insist.mybatis.config;

import com.jdbc.insist.mybatis.utils.GenericTokenParser;
import com.jdbc.insist.mybatis.utils.ParameterMappingTokenHandler;

/**
 * @ClassName: SqlSource
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 16:40
 */
public class SqlSource {

    private String sqlText;

    public SqlSource(String sqlText) {
        this.sqlText = sqlText;
    }

    public BoundSql getBoundSql() {
        // 解析sql文本
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = genericTokenParser.parse(sqlText);
        return new BoundSql(sql, tokenHandler.getParameterMappings());
    }
}
