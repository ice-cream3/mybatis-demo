package com.jdbc.insist.controller;

import com.jdbc.insist.entity.User;
import com.jdbc.insist.mybatis.sqlsession.SqlSessionFactory;
import com.jdbc.insist.mybatis.sqlsession.SqlSessionFactoryBuilder;
import com.jdbc.insist.service.UserService;
import com.jdbc.insist.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @ClassName: UserServiceTese
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 17:09
 */
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findUserTest() {
        UserService userService = new UserServiceImpl(sqlSessionFactory);
        User user = userService.findById(100);
        logger.info(user.toString());
    }
}
