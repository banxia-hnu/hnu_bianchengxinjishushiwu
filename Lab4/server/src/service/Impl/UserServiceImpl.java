package service.Impl;

import Lab1.DBConnector;
import Lab1.OperatingUser;
import Lab1.User;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    OperatingUser opU=new OperatingUser();
    @Override
    public int verifyLogin(User u, DBConnector dButil)throws SQLException {
        boolean zero=false;
        boolean one=false;
        try {
            if(opU.checkUserPassword(u, dButil)==0){
                zero=true;
            }
            else if(opU.checkUserPassword(u, dButil)==1){
                one=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(zero) return 0;
        else if(one) return 1;
        else return -1;
    }

    @Override
    public boolean addUser(User u, DBConnector dButil) {
        boolean isok = false;
        try{
            opU.addUser(u,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(opU.checkUserPassword(u, dButil)==1)
                isok=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isok;
    }

    @Override
    public boolean changePassword(User u, DBConnector dButil){
        boolean isok = false;
        try{
            opU.updateUser(u,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(opU.checkUserPassword(u, dButil)==1)
                isok=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isok;
    }

    @Override
    public boolean isRegistered(String username, DBConnector dButil) {
        boolean isRegister=false;
        try{
            isRegister=opU.findUser(username,dButil);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isRegister;
    }
}
