package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    final String USER = "root";
    final String PASS = "root";
    final String URL = "jdbc:mysql://127.0.0.1:3306/myDBtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            Statement statement =  connection.createStatement();
            String sqlCommand = "CREATE TABLE IF NOT EXISTS Users (Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(30), age TINYINT)";
            statement.executeUpdate(sqlCommand);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.connect();
            Statement statement =  connection.createStatement()) {
            String sqlCommand = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sqlCommand);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
        try(Connection connection = Util.connect();
            PreparedStatement preparedStatement =  connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.connect();
            Statement statement =  connection.createStatement()) {
            String sqlCommand = "DELETE FROM Users WHERE id = id";
            statement.executeUpdate(sqlCommand);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new LinkedList<>();
        try(Connection connection = Util.connect();
            Statement statement =  connection.createStatement()) {
            ResultSet resultset = statement.executeQuery("SELECT * FROM Users");
            while(resultset.next()) {
                User user = new User(resultset.getString("name"),
                        resultset.getString("lastName"),
                        resultset.getByte("age"));
                user.setId(resultset.getLong("id"));
                list.add(user);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.connect();
            Statement statement =  connection.createStatement()) {
            String sqlCommand = "DELETE FROM Users";
            statement.executeUpdate(sqlCommand);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
