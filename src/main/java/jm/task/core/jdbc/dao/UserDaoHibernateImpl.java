package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.*;


import java.util.ArrayList;
import java.util.Iterator;
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
            Transaction t = session.beginTransaction();
            session.save(new User( name, lastName, age));
            t.commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void removeUserById(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from testUser.user where id=:id");
            deleteQuery.setParameter("id", id);
            deleteQuery.executeUpdate();
            tx.commit();
            session.close();
        } catch (HibernateException e) {
       e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<User> userList = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            String sql = "SELECT * FROM testUser.user";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(User.class);
            List users = query.list();

            for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                User user = (User) iterator.next();
                userList.add(user);
                System.out.print("First Name: " + user.getName());
                System.out.print("  Last Name: " + user.getLastName());
                System.out.println("  age: " + user.getAge());
            }
            tx.commit();
        } catch (HibernateException e) {
        e.printStackTrace();
        }
        return userList;
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
