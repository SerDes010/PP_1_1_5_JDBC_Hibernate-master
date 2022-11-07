package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
       Util.getConnection();
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        User user1 = new User("Nikolay ", "Nikolaev", (byte) 25);
        User user2 = new User("Andrey ", "Andreev", (byte) 35);
        User user3 = new User("Sergey ", "Sergeev", (byte) 45);
        User user4 = new User("Petr ", "Petrov", (byte) 50);



        us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> list = new ArrayList<>(us.getAllUsers());
        for (User u : list) {
            System.out.println(u);
        }

        us.removeUserById(1);
        us.removeUserById(2);

        List<User> list2 = new ArrayList<>(us.getAllUsers());
        for (User u : list2) {
            System.out.println(u);
        }

        us.cleanUsersTable();
        List<User> list3 = new ArrayList<>(us.getAllUsers());
        for (User user : list3) {
            System.out.println(user);
        }

        us.dropUsersTable(); // реализуйте алгоритм здесь
   }
}
