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
import java.util.List;

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
        // 全局配置文件
        String resource = "SqlMapConfig.xml";
        // 获取配置文件流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        /**
         * 解析文件流获取配置信息(Configuration,dataSource,connection,statement)
         * 通过构建者模式创建sqlSessionFactory
         */
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findUserTest() {
        // 入口接口.要想执行一个sql首先要有sqlSession,sqlSession通过sqlSessionFactory创建,转init方法
        UserService userService = new UserServiceImpl(sqlSessionFactory);
        User param = new User();
        param.setId(100);
        param.setAge(18);
        List<User> users = userService.findListByAge(param);
        logger.info("年龄18的用户有{}个", users.size());
        User user = userService.find(param);
        if (null != user) {
            logger.info("user={}", user.toString());
        }
        logger.info("查询数据:{}", user);
    }

}
