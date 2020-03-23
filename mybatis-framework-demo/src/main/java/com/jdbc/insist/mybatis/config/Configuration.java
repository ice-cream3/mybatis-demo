package com.jdbc.insist.mybatis.config;

import lombok.Data;

import javax.sql.DataSource;

/**
 * @ClassName: Configration
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/22 17:51
 */
@Data
public class Configuration {

    private DataSource dataSource;
}
