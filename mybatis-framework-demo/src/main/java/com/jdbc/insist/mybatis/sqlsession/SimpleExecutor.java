package com.jdbc.insist.mybatis.sqlsession;

import com.jdbc.insist.mybatis.config.*;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SimpleExecutor
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 17:38
 */
public class SimpleExecutor implements Executor {

    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object params) {
        Connection connection;
        List<Object> returnList = new ArrayList<Object>();
        try {
            // 通过configuration获取数据源
            DataSource dataSource = configuration.getDataSource();
            // 通过数据源获取连接
            connection = dataSource.getConnection();
            // 通过mappedStatement获取sqlSource
            SqlSource sqlSource = mappedStatement.getSqlSource();
            // 获取sql及参数
            BoundSql boundSql = sqlSource.getBoundSql();
            // 获取sql
            String sql = boundSql.getSql();

            // 获取statementType类型
            String statementType = mappedStatement.getStatementType();
            if ("prepared".equals(statementType)) {
                // 输入sql
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // 获取参数
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                // 获取入参类型
                Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
                // 基本类型
                if (parameterTypeClass == Integer.class) {
                    preparedStatement.setObject(1, params);
                } else {
                    // 查询参数处理,并执行
                    pojoQueryParameter(params, preparedStatement, parameterMappings, parameterTypeClass);
                    // 结果集处理,封装返回结果
                    pojoQueryResult(mappedStatement, returnList, preparedStatement);
                }
            }
            return (List<T>) returnList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void pojoQueryParameter(Object params, PreparedStatement preparedStatement, List<ParameterMapping> parameterMappings, Class<?> parameterTypeClass) throws NoSuchFieldException, IllegalAccessException, SQLException {
        // pojo类型,对查询入参进行处理
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            // 获取参数名称
            String name = parameterMapping.getName();
            // 反射获取入参值
            Field declaredField = parameterTypeClass.getDeclaredField(name);
            declaredField.setAccessible(true);
            Object value = declaredField.get(params);
            // 输入sql查询的值
            preparedStatement.setObject(i+1, value);
        }
    }

    private void pojoQueryResult(MappedStatement mappedStatement, List<Object> returnList, PreparedStatement preparedStatement) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 进行查询
        ResultSet resultSet = preparedStatement.executeQuery();
        // 返回结果集类型
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        // 对返回结果进行处理
        while (resultSet.next()) {
            // 实例一个返回对象
            Object returnObj = resultTypeClass.newInstance();
            // 获取结果集中第一个列对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                // 获取列名称,index从1开始
                String columnName = metaData.getColumnName(i+1);
                // 反射封装结果集
                Field declaredField = resultTypeClass.getDeclaredField(columnName);
                declaredField.setAccessible(true);
                declaredField.set(returnObj, resultSet.getObject(i+1));
            }
            // 放入返回对象中
            returnList.add(returnObj);
        }
    }
}
