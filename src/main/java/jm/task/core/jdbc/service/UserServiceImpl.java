package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDaoHibernate = new UserDaoHibernateImpl();


    @Override
    public Util getUtil() {
        return userDaoHibernate.getUtil();
    }

    public UserServiceImpl() throws SQLException {
    }

    //создание таблицы Users
    public void createUsersTable() throws SQLException {
        userDaoHibernate.createUsersTable();
    }

    //удаление таблицы Users
    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();
    }

    //добавление юзера в таблицу
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name, lastName, age);
    }

    //удаление юзера из таблицы по id
    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);
    }

    //сохранение всех юзеров в лист
    public List<User> getAllUsers() throws SQLException {
        return userDaoHibernate.getAllUsers();
    }

    //очистка таблицы Users
    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();
    }
}
