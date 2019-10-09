package sample.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private static final String conStr = "jdbc:mysql://localhost:3307/mytestdb" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection conn;

    public  static  void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(conStr,
                "root","root");
    }


}


//package sample.database;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DbConfig {
//
//    private static final String conStr = "jdbc:mysql://ipAddress:port/chat" +
//            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//
//    public static Connection conn;
//
//    public  static  void createConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        conn = DriverManager.getConnection(conStr,
//                "user","password");
//    }
//
//
//}
