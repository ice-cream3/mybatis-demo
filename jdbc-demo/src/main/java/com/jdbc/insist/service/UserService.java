package com.jdbc.insist.service;

import com.jdbc.insist.entity.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    List<User> findListById(int id);
}
