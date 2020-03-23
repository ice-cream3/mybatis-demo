package com.jdbc.insist.dao;

import com.jdbc.insist.entity.User;
import com.jdbc.insist.mybatis.sqlsession.SqlSession;
import com.jdbc.insist.mybatis.sqlsession.SqlSessionFactory;

import java.util.List;

/**
 * @ClassName: UserDao
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 16:56
 */

public class UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDao() {};
    public UserDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public User findById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("insist.findById", id);
        return user;
    }

    public List<User> findListById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("insist.findListById", id);
        return userList;
    }
}
