package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.UtilJDBC;
import org.hibernate.*;

import java.sql.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
         User user1 = new User("Daneen","Seisenbay", (byte) 7);
         User user2 = new User("Chingis","Montay", (byte) 32);
         User user3 = new User("Zarina","Dosmail", (byte) 30);
         User user4 = new User("Alaya","Seisenbay", (byte) 5);
        List<User>userList = Arrays.asList(user1,user2,user3,user4);

        for (User user : userList) {
            userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        }
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        HibernateUtil.getSessionFactory().close();


    }
}
