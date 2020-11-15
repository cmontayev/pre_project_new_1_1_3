package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.createSQLQuery("create table if not exists user" +
                    "(id int auto_increment primary key," +
                    "name varchar(30)," +
                    "lastName varchar(30)," +
                    "age int )").executeUpdate();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.createSQLQuery("DROP TABLE IF EXISTS user ").executeUpdate();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            User user = new User(name,lastName,age);
            session.persist(user);
            System.out.println("Employee saved");
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.delete(session.get(User.class, id));
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.createSQLQuery("TRUNCATE TABLE user ").executeUpdate();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
