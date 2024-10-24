package com.gastos.seguimiento.controller;
import org.bson.types.ObjectId;

import com.gastos.seguimiento.controller.user.UserController;
import com.gastos.seguimiento.model.user.User;
import com.gastos.seguimiento.service.user.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // Test para obtener un usuario por ID
    @Test
    public void testGetUserById() {
        String userId = "123";
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setName("Test User");

        when(userService.findById(userId)).thenReturn(mockUser);

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test User", response.getBody().getName());
    }

    // Test para obtener todos los usuarios
    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId("1");
        user1.setName("User 1");

        User user2 = new User();
        user2.setId("2");
        user2.setName("User 2");

        List<User> mockUserList = Arrays.asList(user1, user2);

        when(userService.findAll()).thenReturn(mockUserList);

        List<User> result = userController.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("User 1", result.get(0).getName());
    }

    // Test para guardar un usuario
    @Test
    public void testSaveUser() {
        User mockUser = new User();
        mockUser.setName("Nuevo Usuario");

        when(userService.save(any(User.class))).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createUser(mockUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Nuevo Usuario", response.getBody().getName());
    }





}
