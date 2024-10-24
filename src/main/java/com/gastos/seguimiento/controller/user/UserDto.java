package com.gastos.seguimiento.controller.user;

public class UserDto {
    private final String name;
    private final String email;
    private final String telefono;
    private final String direccion;
    private final String password;

    public UserDto() {
        this.name = "";
        this.email = "";
        this.telefono = "";
        this.direccion = "";
        this.password = "";
    }

    public UserDto(String name, String email, String telefono, String direccion, String password) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPassword() {
        return password;
    }
}
