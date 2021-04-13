package com.zjw.service.impl;

import com.zjw.Person;
import com.zjw.PersonDao;
import com.zjw.service.PersonService;
import com.zjw.DButil;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonDao personDao = new PersonDao();

    @Override
    public int addOrModifyPerson(Person person,DButil dButil) {
        int ans=-1;
        try{
            ans = personDao.addOrModifyPerson(person,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public List<Person> queAll(DButil dButil) {
        List<Person> list = null;
        try{
            list = personDao.queAll(dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
