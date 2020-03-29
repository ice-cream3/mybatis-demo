package com.jdbc.insist.mybatis.config;

/**
 * @ClassName: ParameterMapping
 * @Description:
 * @Author: lixl
 * @Date: 2020/3/28 16:44
 */
public class ParameterMapping {

    private String name;

    public ParameterMapping(String content) {
        this.name = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
