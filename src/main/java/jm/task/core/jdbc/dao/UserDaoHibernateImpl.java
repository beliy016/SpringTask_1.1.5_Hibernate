package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Util util = new Util();
    Transaction transaction;
    Session session;
    SessionFactory factory;

    private static final String QUERY_NEW_TABLE = "CREATE TABLE users(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "    name     varchar(45)  not null," +
            "    lastName varchar(256) not null," +
            "    age      TINYINT      not null) ENGINE=MyISAM;";
    private static final String QUERY_REMOVE_TABLE = "DROP TABLE users";
    private static final String QUERY_CLEAN_TABLE = "TRUNCATE TABLE users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory factory = util.getSessionFactory()) {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_NEW_TABLE).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory factory = util.getSessionFactory()) {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_REMOVE_TABLE).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory factory = util.getSessionFactory()) {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory factory = util.getSessionFactory()) {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();
            User us = session.get(User.class, id);
            session.delete(us);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (SessionFactory factory = util.getSessionFactory()) {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory factory = util.getSessionFactory()) {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_CLEAN_TABLE).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
