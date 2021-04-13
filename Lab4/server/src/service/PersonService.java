package service;

import Lab1.DBConnector;
import Lab1.Person;

public interface PersonService {
    public boolean addPerson (Person p, DBConnector dButil);
    public boolean isCorrectTelenum(String username, String telenum, DBConnector dButil);
    public Person getPerson(String username, DBConnector dButil);
    public boolean isRegistered(String username,DBConnector dButil);
}
