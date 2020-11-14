package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Util;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.creatConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User" +
                    "(id int auto_increment primary key," +
                    "name varchar(30)," +
                    "lastName varchar(30)," +
                    "age int )");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
     try (Statement statement = Util.creatConnection().createStatement()){
         statement.executeUpdate("DROP TABLE if exists User");
     }catch (SQLException e){
         e.printStackTrace();
     }
    }

    public void saveUser(String name, String lastName, byte age) {
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {

        return null;
    }

    public void cleanUsersTable() {

    }
}
