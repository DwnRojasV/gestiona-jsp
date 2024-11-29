package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String USER_TABLE = "usuario";
    private static final String USER_NAME_COLUMN = "nombre";
    private static final String USER_EMAIL_COLUMN = "email";
    private static final String USER_PASSWORD_COLUMN = "password_hash";
    private static final String USER_REGISTRATION_DATE_COLUMN = "fecha_registro";


    public User login(String email, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();

        try {
            connection = mySQLConnection.getConnection();
            String query = String.format("SELECT * FROM %s u WHERE u.email=? AND u.password_hash=?;", USER_TABLE);
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            result = statement.executeQuery();

            while (result.next()) {
                user = new User();
                user.setIdUser(result.getInt("id_usuario"));
                user.setName(result.getString("nombre"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password_hash"));
                user.setFechaRegistro(result.getTimestamp("fecha_registro"));
            }

        } catch (Exception error) {
            System.out.println("Something went wrong " + error.getMessage());
            error.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception error) {
                System.out.println("Something went wrong" + error.getMessage());
                error.printStackTrace();
            }
        }
        return user;
    }

    public Boolean signup(String email, String name, String password) throws CustomException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ConnectionMySQL ConMySQL = new ConnectionMySQL();
        String hashedPassword = null;
        int generatedId = 0;

        if (email == null) {
            throw new CustomException(CustomException.EMAIL_NULL_ERROR);
        }
        if (password == null) {
            throw new CustomException(CustomException.PASSWORD_NULL_ERROR);
        } else {
            hashedPassword = Password.encryptPassword(password);
        }


        try {
            connection = ConMySQL.getConnection();
            String query = String.format(
                    "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, NOW())",
                    USER_TABLE,
                    USER_NAME_COLUMN,
                    USER_EMAIL_COLUMN,
                    USER_PASSWORD_COLUMN,
                    USER_REGISTRATION_DATE_COLUMN
            );
            if (hashedPassword != null) {
                statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, hashedPassword);

                boolean hasResult = statement.execute();
                if (!hasResult) {
                    int affectedRows = statement.getUpdateCount();
                    if (affectedRows > 0) {
                        try (ResultSet rs = statement.getGeneratedKeys()) {
                            if (rs.next()) {
                                generatedId = rs.getInt(1); // Obtén la clave generada si hay alguna
                                System.out.println("Inserción exitosa. ID generado: " + generatedId);
                            }
                        }
                    } else {
                        System.out.println("La inserción no afectó ninguna fila.");
                    }
                } else {
                    System.out.println("la consulta produjo un ResulSet");
                }

            } else {
                throw new CustomException(CustomException.PASSWORD_NULL_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Something went wrong" + e.getMessage());
                e.printStackTrace();
            }
        }
        return generatedId != 0;
    }
}

