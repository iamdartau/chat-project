package sample.repository;

import sample.database.DbConfig;
import sample.model.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UsersRep implements IUsersRep {

    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public UsersRep(){
        try {
            DbConfig.createConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserbyloginandPassword(String login, String password) throws SQLException {

        preparedStatement = DbConfig.conn.prepareStatement
                ("select * from users where login = ? and password = ?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);


        resultSet = preparedStatement.executeQuery();

        String login_new = null;
        String password_new = null;
        Integer id_new = null;
        String name_new = null;
        String surname_new = null;
        Timestamp time_reg_new = null;
        Timestamp last_activity_new = null;
        boolean status = false;


        while (resultSet.next())
        {
            id_new = resultSet.getInt("id");
            login_new = resultSet.getString("login");
            password_new = resultSet.getString("password");
            name_new= resultSet.getString("name");
            surname_new = resultSet.getString("surname");
            time_reg_new = resultSet.getTimestamp("time_reg");
            last_activity_new = resultSet.getTimestamp("last_activity");
            status = (resultSet.getString("status").equals("Y"))? true:false;
        }

        if (id_new == null)
            return null;
        return new User (id_new, name_new, surname_new, login_new, password_new, time_reg_new, last_activity_new, (status)?"Y":"N");



    }


    @Override
    public void sign_up(User user) throws SQLException {

        preparedStatement = DbConfig.conn.prepareStatement
                ("insert into users (name, surname, login, password, time_reg, last_activity, status) values(?, ?, ?, ?, ?,?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        java.util.Date date = new java.util.Date();
        preparedStatement.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
        preparedStatement.setTimestamp(6, new java.sql.Timestamp(date.getTime()));
        preparedStatement.setString(7,(user.getStatus())?"Y":"N");

        preparedStatement.executeUpdate();

    }
//    id, name,surname,login,password,status

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        String query = "select * from users";
        statement = DbConfig.conn.createStatement();

        resultSet = statement.executeQuery(query);
        //System.out.println("result");
        while (resultSet.next())
        {
//
//            System.out.println(resultSet.getInt("id"));
//            System.out.println(resultSet.getString("name"));
//            System.out.println(resultSet.getString("surname"));
//            System.out.println(resultSet.getString("status"));
//            User user = new User(1, "testt","test","test","login","Y");
//            System.out.println(user);
            list.add(new User(resultSet.getInt("id"),
                    resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("login"), resultSet.getString("password"),
                    resultSet.getTimestamp("time_reg"), resultSet.getTimestamp("last_activity"),
                    resultSet.getString("status")));
        }
        return  list;

    }
/*
    public String isOnline (String login) throws SQLException

    {
        preparedStatement = DbConfig.conn.prepareStatement(
                "select status from users where login = ?");
        preparedStatement.setString(1, login);

        resultSet = preparedStatement.executeQuery();

        return resultSet.getString("status");

    }
*/

    @Override
    public int getByLogin(String login) throws SQLException
    {
        preparedStatement = DbConfig.conn.prepareStatement(
                "select id from users where login = ?");
        preparedStatement.setString(1, login);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    @Override
    public void updateStatusOnYes(int userId) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "update users set status = 'Y' where id = ?");
        preparedStatement.setInt(1,userId);
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateStatusOnNo(int userId) throws SQLException {
        preparedStatement = DbConfig.conn.prepareStatement(
                "update users set status = 'N' where id = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
    }

}
