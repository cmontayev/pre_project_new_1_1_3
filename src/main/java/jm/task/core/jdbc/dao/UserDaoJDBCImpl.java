package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.UtilJDBC;
import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = UtilJDBC.creatConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user" +
                    "(id int auto_increment primary key," +
                    "name varchar(30)," +
                    "lastName varchar(30)," +
                    "age int )");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = UtilJDBC.creatConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE if exists user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStmt = UtilJDBC.creatConnection()
                .prepareStatement(" insert into testUser.user (name, lastName, age)"
                        + " values (?, ?, ?)")) {

            preparedStmt.setString(1, name);
            preparedStmt.setString(2, lastName);
            preparedStmt.setLong(3, age);
            preparedStmt.executeUpdate();
            printUser(preparedStmt);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void printUser(Statement statement){
        try {
            final ResultSet resultSet = statement.executeQuery("SELECT  name FROM testUser.user WHERE id = LAST_INSERT_ID()");
                       while (resultSet.next()){
                           System.out.println("User with name "+ resultSet.getString("name")+" добавлен в базу данных ");
                       }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStmt = UtilJDBC.creatConnection()
                .prepareStatement("delete from testUser.user where id = ?")) {
            preparedStmt.setLong(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = UtilJDBC.creatConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM testUser.User");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                userList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = UtilJDBC.creatConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
