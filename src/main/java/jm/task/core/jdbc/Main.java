package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Kostya", "Vasilev", (byte) 31);
        User user2 = new User("Anna", "Bolotny", (byte) 56);
        User user3 = new User("Olga", "Bolotny", (byte) 46);
        User user4 = new User("Mavludahon", "Namil-ogly", (byte) 26);

        final UserService userService = new UserServiceImpl();
        //userService.dropUsersTable();

        userService.createUsersTable();
        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        System.out.printf("user %s was added into Users table\n", user1.getName());
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        System.out.printf("user %s was added into Users table\n", user2.getName());
        userService.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        System.out.printf("user %s was added into Users table\n", user3.getName());
        userService.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        System.out.printf("user %s was added into Users table\n", user4.getName());

        List<User> users = userService.getAllUsers();
        for(User a : users) {
            System.out.println(a);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
