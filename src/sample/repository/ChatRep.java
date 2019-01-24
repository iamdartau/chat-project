package sample.repository;

import sample.database.DbConfig;
import sample.model.Chat;
import sample.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChatRep implements IChatRep {

    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public ArrayList<Chat> getAllChats() throws SQLException {
        ArrayList <Chat> ChatList = new ArrayList<>();

        statement = DbConfig.conn.createStatement();
        resultSet = statement.executeQuery("select * from chats");
        while (resultSet.next()){
            Chat chat = new Chat(resultSet.getInt("id"),
                    resultSet.getString("name"));
            chat.setUsersChat(chat.getUsersChat());
            ChatList.add(chat);
        }

        return ChatList;
    }

    @Override
    public int insertChat(String name) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "insert into chats (name) values (?)");
        preparedStatement.setString(1,name);
        preparedStatement.executeUpdate();
        return getChatId(name);

//
//        getAllChats();
    }

    @Override
    public ArrayList<User> getUsersChat(int chatId) throws SQLException {
        ArrayList<User> usersChat = new ArrayList<>();
        preparedStatement = DbConfig.conn.prepareStatement
                ("select t1.* from users t1, user_chat t2 where t1.id = t2.user_id " +
                        "and t2.chat_id = (?)");
        preparedStatement.setInt(1,chatId);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            usersChat.add(new User(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getTimestamp("time_reg"),
                    resultSet.getTimestamp("last_activity"),
                    resultSet.getString("string")));

        }
        return usersChat;
    }

    @Override
    public int getChatId(String name) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement("select id from chats where name = ?");
        preparedStatement.setString(1,name);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }
}
