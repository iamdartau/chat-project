package sample.repository;

import sample.model.Chat;
import sample.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IChatRep {

    ArrayList <Chat> getAllChats () throws SQLException;

    int insertChat(String name) throws SQLException;

    ArrayList<User> getUsersChat(int chatId) throws SQLException;

    int getChatId(String name) throws SQLException;
}
