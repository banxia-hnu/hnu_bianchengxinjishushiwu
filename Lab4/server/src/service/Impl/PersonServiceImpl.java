package service.Impl;

import Lab1.DBConnector;
import Lab1.OperatingPerson;
import Lab1.Person;
import service.PersonService;

public class PersonServiceImpl implements PersonService {
    OperatingPerson opP=new OperatingPerson();
    @Override
    public boolean addPerson(Person p, DBConnector dButil) {
        boolean isok = true;
        try{
           opP.addPerson(p,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isok;
    }

    @Override
    public boolean isCorrectTelenum(String username, String telenum, DBConnector dButil) {
        boolean isCorrect = false;
        try{
            if(opP.checkTelephone(new Person(username,"",0,telenum),dButil)==1){
                isCorrect=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isCorrect;
    }

    @Override
    public Person getPerson(String username, DBConnector dButil) {
        Person p=new Person(username,"");
        try {
             p=opP.getPerson(username, dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public boolean isRegistered(String username, DBConnector dButil) {
        boolean isRegister=false;
        try{
            isRegister=opP.findPerson(username,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isRegister;
    }
}
