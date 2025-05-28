package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService us = new UserServiceImpl();



        us.createUsersTable();

        us.saveUser("Ivan", "Petrov", (byte) 36);
        us.saveUser("Oleg", "Ivanov", (byte) 38);
        us.saveUser("Petr", "Sidorov", (byte) 55);
        us.saveUser("Denis", "Belov", (byte) 18);

        List<User> list = us.getAllUsers();
        for (User u : list) {
            System.out.println(u);
        }

        us.cleanUsersTable();

        us.dropUsersTable();

        Util.closeSessionFactory(us.getSF());
    }
}
