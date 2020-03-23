package com.jdbc.insist.mybatis.sqlsession;

import java.util.List;

public interface SqlSession {

    <T> T selectOne(String statementId, Object params);

    <T> List<T> selectList(String statementId, Object params);
}
