package sample.repository;

import java.sql.SQLException;

public interface IMeesegeRep
{
    String getAllMessage(int chatName) throws SQLException;

    void insertMessege (int userChatId, String messege) throws SQLException;
    int getUserChatId(int chatId, int userId) throws SQLException;

    int getCount(int userId) throws SQLException;

}
