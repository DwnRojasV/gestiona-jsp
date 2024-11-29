package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User Login(String email, String password){
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();

        try {
            connection = mySQLConnection.getConnection();
            String query = "SELECT * FROM usuario u WHERE u.email=? AND u.password_hash=?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2, password);
            result = statement.executeQuery();

            while (result.next()){
                user = new User();
                user.setIdUser(result.getInt("id_usuario"));
                user.setName(result.getString("nombre"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password_hash"));
                user.setFechaRegistro(result.getTimestamp("fecha_registro"));
            }

        } catch (Exception error){
            System.out.println("Something went wrong " + error.getMessage());
            error.printStackTrace();
        } finally {
            try{
                if (result != null){
                    result.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (Exception error){
                System.out.println("Something went wrong" + error.getMessage());
                error.printStackTrace();
            }
        }
        return  user;
    }
}
