package com.zjw.service;

import com.zjw.User;
import com.zjw.DButil;

import java.util.List;

public interface UsersService {
    //void addUser(String user);
    int delUser(String username, DButil dButil);
    List<User> queAll(DButil dButil);
}
