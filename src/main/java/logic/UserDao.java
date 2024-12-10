package logic;

import java.sql.*;
import java.util.ArrayList;

import static constants.AppConstants.*;

public class UserDao {


    public User login(String email, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();

        try {
            connection = mySQLConnection.getConnection();
            String query = String.format("SELECT * FROM %s u WHERE u.email=?;", USER_TABLE);
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            result = statement.executeQuery();

            while (result.next()) {
                int userId = result.getInt("id_usuario");
                String userName = result.getString("nombre");
                String userEmail = result.getString("email");
                String userHashedPassword = result.getString("password_hash");
                Timestamp userRegisterDate = result.getTimestamp("fecha_registro");

                //verificando que la contraseña ingresada y la almacenada sean iguales o coincidan.
                Boolean isValidPassword = Password.validatePassword(password, userHashedPassword);

                if (isValidPassword) {
                    user = new User();
                    user.setIdUser(userId);
                    user.setName(userName);
                    user.setEmail(userEmail);
                    user.setHashedPassword(userHashedPassword);
                    user.setFechaRegistro(userRegisterDate);
                }
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

    public Boolean updateUser(User oldUser, String newName, String newHashedPassword, String newPhone) throws CustomException, SQLException {
        PreparedStatement statement = null;
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();
        int countAffectedRows = 0;

        if (oldUser == null) {
            throw new CustomException(CustomException.USER_NULL_ERROR);
        }

        try (Connection connection = mySQLConnection.getConnection()) {
            String query = String.format("UPDATE %s u SET u.%s=?, u.%s=?, u.%s=? WHERE u.%s=?",
                    USER_TABLE,
                    USER_NAME_COLUMN,
                    USER_PASSWORD_COLUMN,
                    USER_PHONE_COLUMN,
                    USER_ID_COLUMN);
            statement = connection.prepareStatement(query);
            statement.setString(1, newName);
            statement.setString(2, newHashedPassword);
            statement.setString(3, newPhone);
            statement.setInt(4, oldUser.getIdUser());
            countAffectedRows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countAffectedRows != 0;
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();

        try (Connection connection = mySQLConnection.getConnection()) {
            String query = String.format("SELECT  * FROM %s", USER_TABLE);
            PreparedStatement statment = connection.prepareStatement(query);
            ResultSet result = statment.executeQuery();

            while (result.next()) {
                int idUser = result.getInt(USER_ID_COLUMN);
                String name = result.getString(USER_NAME_COLUMN);
                String email = result.getString(USER_EMAIL_COLUMN);
                String role = result.getString(USER_ROLE_COLUMN);
                String permission = result.getString(USER_PERMISSION_COLUMN);
                User user = new User(idUser, name, email, role, permission);
                users.add(user);
            }
        }

        return users;
    }

    public Boolean updateUser(int id, String userName, String userEmail, String userRole, String userPermission) {
        Boolean isUpdated = false;
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();
        try (Connection connection = mySQLConnection.getConnection()) {
            String query = String.format("UPDATE %s u SET u.%s=?, u.%s=?, u.%s=?, u.%s=?  WHERE u.%s=?;",
                    USER_TABLE,
                    USER_NAME_COLUMN,
                    USER_EMAIL_COLUMN,
                    USER_ROLE_COLUMN,
                    USER_PERMISSION_COLUMN,
                    USER_ID_COLUMN);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, userEmail);
            statement.setString(3, userRole);
            statement.setString(4, userPermission);
            statement.setInt(5, id);

            int affectedRows = statement.executeUpdate();

            isUpdated = affectedRows > 0;
            System.out.println("User updated successfully");

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }

        return isUpdated;
    }

    public Boolean deleteUserById(int id) throws SQLException {
        ConnectionMySQL mySQLConnection = new ConnectionMySQL();
        Boolean isDeleted = false;

        try (Connection connection = mySQLConnection.getConnection()) {
            String query = String.format("DELETE FROM %s u WHERE u.%s=?", USER_TABLE, USER_ID_COLUMN);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            isDeleted = affectedRows > 0;
            if (isDeleted) {
                System.out.println("Deleted Successfully");
            } else {
                System.out.println("Something went wrong");
            }
        }
        return isDeleted;
    }
}

