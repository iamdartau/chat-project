package sample.repository;

import sample.database.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserChatRep implements IUserChat
{
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    @Override
    public int insertIntoUsersChat(int chatId, int userId) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement
                ("insert into user_chat (chat_id, user_id)" +
                        "values (?, ?)");
        preparedStatement.setInt(1,chatId);
        preparedStatement.setInt(2, userId);
        preparedStatement.executeUpdate();

        return getUserChatId(chatId, userId);
    }

    @Override
    public int getUserChatId(int chatId, int userId) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "select id from user_chat where chat_id = ? and user_id = ?");
        preparedStatement.setInt(1,chatId);
        preparedStatement.setInt(2,userId);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }


}
