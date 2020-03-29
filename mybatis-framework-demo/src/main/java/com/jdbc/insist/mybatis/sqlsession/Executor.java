package com.jdbc.insist.mybatis.sqlsession;

import com.jdbc.insist.mybatis.config.Configuration;
import com.jdbc.insist.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @ClassName: Executor
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 17:30
 */
public interface Executor {

    <T> List<T> query(Configuration configuration, MappedStatement statement, Object params);
}
