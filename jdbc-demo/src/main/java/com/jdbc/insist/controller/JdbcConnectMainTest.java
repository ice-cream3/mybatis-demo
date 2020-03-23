package com.jdbc.insist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class JdbcConnectMainTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcConnectMainTest.class);

    /**
     * 简单的查询
     * @param args
     */
    public static void main(String[] args) {
        try {
            String character = "?characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo","root","root");
//            PreparedStatement preparedStatement = connection1.prepareStatement("select * from user where id = ?");
//            preparedStatement.setInt(1,100);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                int age = resultSet.getInt("age");
//                String name = resultSet.getString("name");
//                System.out.println("id="+id+",age="+age+",name="+name);
//            }


//            Connection connection = getConnection();
//            handleStatement(connection);
//            logger.info("查询结束");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void handleStatement(Connection connection) throws SQLException {
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 100);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("id="+resultSet.getInt("id")+",name="+resultSet.getString("name"));
            logger.info("id={}, name={}", resultSet.getInt("id"), resultSet.getString("name"));
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        Class.forName(driver);
        return DriverManager.getConnection(url,"root", "root");
    }



}
