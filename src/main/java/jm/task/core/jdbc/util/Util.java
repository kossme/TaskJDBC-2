package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    final static String USER = "root";
    final static String PASS = "root";
    final static String URL = "jdbc:mysql://127.0.0.1:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
