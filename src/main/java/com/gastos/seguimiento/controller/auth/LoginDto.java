package com.gastos.seguimiento.controller.auth;

public class LoginDto {

    private String email;
    private String password;

    // Constructor vacío
    public LoginDto() {}

    // Constructor con parámetros
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y setters
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
}
