package com.zjw.service;

import com.zjw.Person;
import com.zjw.DButil;

import java.util.List;


public interface PersonService {
    int addOrModifyPerson(Person person, DButil dButil);
    //void addaddOrModifyPerson(Person person);
    //void delPersonOnUsername(String username);
    List<Person> queAll(DButil dButil);
}
