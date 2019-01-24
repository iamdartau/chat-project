package sample.repository;

import sample.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUsersRep {

     User getUserbyloginandPassword (String login, String password) throws SQLException;
     void sign_up (User user) throws SQLException;
     ArrayList<User> getAll() throws SQLException;
     int getByLogin(String name) throws SQLException;
     void updateStatusOnYes(int userId) throws SQLException;
     void updateStatusOnNo (int userId) throws SQLException;

}
