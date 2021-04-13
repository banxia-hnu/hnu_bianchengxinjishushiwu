package com.zjw.service.impl;

import com.zjw.User;
import com.zjw.UserDao;
import com.zjw.PersonDao;
import com.zjw.service.UsersService;
import com.zjw.DButil;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private UserDao userDao = new UserDao();
    private PersonDao personDao = new PersonDao();
    @Override
    public int delUser(String username ,DButil dButil) {//成功删除返回1
        int isok = 0;
        try{
            if(userDao.delUserOnUsername(username,dButil)>0) isok = 1;
            personDao.delPersonOnUsername(username,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isok;
    }

    @Override
    public List<User> queAll(DButil dButil) {
        List<User> list = null;
        try{
            list = userDao.queAll(dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
