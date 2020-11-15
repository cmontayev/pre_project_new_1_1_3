package jm.task.core.jdbc.util;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilJDBC {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testUser";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "daneen13";



     public static Connection creatConnection(){
         Connection conn  = null;
         try {
             conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         }catch (SQLException e){
             e.printStackTrace();
         }
         return conn;
     }
     public static void closeConnection(){
         try {
             if (creatConnection()!=null){
                 creatConnection().close();
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
     }

}
