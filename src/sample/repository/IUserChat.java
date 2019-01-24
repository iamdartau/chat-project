package sample.repository;

import java.sql.SQLException;

public interface IUserChat
{
    int insertIntoUsersChat(int chatId, int userId) throws SQLException;
    int getUserChatId(int chatId, int userId) throws SQLException;
}
