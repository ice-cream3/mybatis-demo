package com.jdbc.insist.service;

import com.jdbc.insist.dao.UserDao;
import com.jdbc.insist.entity.User;
import com.jdbc.insist.mybatis.sqlsession.SqlSessionFactory;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 16:35
 */
public class UserServiceImpl implements UserService {

    private SqlSessionFactory sqlSessionFactory;

    private UserDao userDao;

    public UserServiceImpl() {}

    public UserServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.userDao = new UserDao(sqlSessionFactory);
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User find(User user) {
        return userDao.find(user);
    }

    public List<User> findListByAge(User user) {
        return userDao.findListByAge(user);
    }
}
