package com.gastos.seguimiento.model.user;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class User {
    @Id
    private String id; // En MongoDB el id es tipo String
    private String name;
    private String email;
    private String telefono;
    private String direccion;
    private String password;
}