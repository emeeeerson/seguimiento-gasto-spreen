package com.gastos.seguimiento.controller.user;

import com.gastos.seguimiento.model.user.User;
import com.gastos.seguimiento.service.user.UserService;
import com.gastos.seguimiento.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Obtener un usuario por ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException.UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo usuario
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userService.save(user); // Lanzará una excepción si el correo ya existe
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (UserException.UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict: El correo ya está en uso
        } catch (UserException.InvalidUserDataException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request: Datos inválidos
        }
    }

    //actualizar un usuario
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            if (updatedUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Asegura que si es null, devolvemos 404
            }
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserException.UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }



    // Eliminar un usuario
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) {
        try {
            userService.delete(id); // Lanzará una excepción si el usuario no existe
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content: Usuario eliminado
        } catch (UserException.UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found: Usuario no encontrado
        }
    }

    @GetMapping("/test")
    public String testUserController() {
        return "UserController is working!";
    }
}
