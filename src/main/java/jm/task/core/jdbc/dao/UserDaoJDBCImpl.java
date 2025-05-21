package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection connection;
    PreparedStatement ps;

    private static final String QUERY_NEW_TABLE = "CREATE TABLE users(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "    name     varchar(45)  not null," +
            "    lastName varchar(256) not null," +
            "    age      TINYINT      not null)";
    private static final String QUERY_REMOVE_TABLE = "DROP TABLE users";
    private static final String QUERY_NEW_USER = "insert into users (name, lastName, age) values (?, ?, ?)";
    private static final String QUERY_REMOVE_USER = "DELETE FROM users WHERE id = ?";
    private static final String QUERY_ALL_USERS = "SELECT * FROM users";
    private static final String QUERY_CLEAN_TABLE = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try {
            connection = util.getConnection();
            ps = connection.prepareStatement(QUERY_NEW_TABLE);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            connection =util.getConnection();
            ps = connection.prepareStatement(QUERY_REMOVE_TABLE);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            connection = util.getConnection();
            ps = connection.prepareStatement(QUERY_NEW_USER);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void removeUserById(long id) throws SQLException {
        try {
            connection = util.getConnection();
            ps = connection.prepareStatement(QUERY_REMOVE_USER);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        try {
            connection = util.getConnection();
            ps = connection.prepareStatement(QUERY_ALL_USERS);
            ResultSet res = ps.executeQuery();
            List<User> list = new ArrayList<>();
            while(res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            connection = util.getConnection();
            ps = connection.prepareStatement(QUERY_CLEAN_TABLE);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
