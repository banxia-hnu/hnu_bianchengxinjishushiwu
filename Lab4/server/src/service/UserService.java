package service;

import Lab1.DBConnector;
import Lab1.User;

import java.sql.SQLException;

public interface UserService {
    public int verifyLogin(User u, DBConnector dButil) throws SQLException;
    public boolean addUser(User u,DBConnector dButil);
    public boolean changePassword(User u,DBConnector dButil);
    public boolean isRegistered(String username,DBConnector dButil);
}
