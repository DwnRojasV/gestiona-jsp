package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.lang.Class.forName;

public class ConnectionMySQL {
    private static final String CONTROLLER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/gestiona";
    private static final String JDBC_USER = "client";
    private static final String JDBC_PASSWORD = "Pass123456";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(CONTROLLER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("Successful Databases Connection");

        } catch (SQLException error) {
            System.out.println("Connection error");
            error.printStackTrace();
        } catch (ClassNotFoundException error){
            System.out.println("Controller Error");
            error.printStackTrace();
        }

        return connection;
    }
}
