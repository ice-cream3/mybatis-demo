package com.jdbc.insist.service;

import com.jdbc.insist.entity.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User find(User user);

    List<User> findListByAge(User user);
}
