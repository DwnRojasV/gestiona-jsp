package logic;

import java.sql.Timestamp;

public class User {
    private int idUser;
    private String name;
    private String email;
    private String hashedPassword;
    private Timestamp fechaRegistro;
    private String phone;
    private String role;
    private String permission;

    public User() {
    }

    public User(
            int idUser,
            String name,
            String email,
            String hashedPassword,
            Timestamp fechaRegistro
    ) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.fechaRegistro = fechaRegistro;
    }

    public User(
            int idUser,
            String name,
            String email,
            String role,
            String permission
    ) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.role = role;
        this.permission = permission;
    }

    public User(String name, String phone, String hashedPassword) {
        this.name = name;
        this.phone = phone;
        this.hashedPassword = hashedPassword;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("User = %s \nId = %d \nEmail = %s",
                this.name,
                this.idUser,
                this.email);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

