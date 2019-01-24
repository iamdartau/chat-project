package sample.repository;

import sample.database.DbConfig;
import sample.model.Messege;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MeesegeRep implements IMeesegeRep {


    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;


    @Override
    public String getAllMessage(int chatId) throws SQLException {
        String text="";
        if (chatId != -1)
        {
            preparedStatement = DbConfig.conn.prepareStatement
                    ("select id, messege_text, time_of_messege from messages where user_chat = ? ");
            preparedStatement.setInt(1,chatId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                text += resultSet.getString("messege_text")+
                        "\t"+resultSet.getString("time_of_messege")+
                        "\n";
            }

        }
        return text;

    }

    @Override
    public void insertMessege(int userChatId, String messege_text) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "insert into messages (user_chat, messege_text," +
                        "time_of_messege ) values (?, ?, ?)");
        preparedStatement.setInt(1, userChatId);
        preparedStatement.setString(2, messege_text);
        java.util.Date date = new java.util.Date();
        preparedStatement.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
        preparedStatement.executeUpdate();

    }

    @Override
    public int getUserChatId(int chatId, int userId) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "select id from user_chat where chat_id = ? and user_id = ?");
        preparedStatement.setInt(1, chatId);
        preparedStatement.setInt(2, userId);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    @Override
    public int getCount(int userChatId) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "select count(id) from messages where user_chat = ?");
        preparedStatement.setInt(1, userChatId);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }


}
