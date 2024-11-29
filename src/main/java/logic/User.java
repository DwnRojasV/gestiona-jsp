package logic;

import java.sql.Timestamp;

public class User {
    private int idUser;
    private String name;
    private String email;
    private String password;
    private Timestamp fechaRegistro;

    public User(){}

    public User(
            int idUser,
            String name,
            String email,
            String password,
            Timestamp fechaRegistro
            ) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return String.format("User = %s \nId = %d \nEmail = %s \nPassword = %s\nDate = %s",
                this.name,
                this.idUser,
                this.email,
                this.password,
                this.fechaRegistro.toString());
    }
}

