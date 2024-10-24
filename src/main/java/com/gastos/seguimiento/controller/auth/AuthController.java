package com.gastos.seguimiento.controller.auth;

import com.gastos.seguimiento.controller.auth.LoginDto;
import com.gastos.seguimiento.controller.auth.TokenDto;
import com.gastos.seguimiento.security.JWTUtil;
import com.gastos.seguimiento.repository.UserRepository;
import com.gastos.seguimiento.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public AuthController() {
        System.out.println("AuthController initialized");
    }


    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        try {
            // Verificar si el email existe en la base de datos
            Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
            if (userOptional.isEmpty()) {
                System.out.println("Usuario no encontrado con el email: " + loginDto.getEmail());
                return ResponseEntity.status(401).build(); // Si no se encuentra el usuario, retorna 401 Unauthorized
            } else {
                System.out.println("Usuario encontrado: " + userOptional.get().getEmail());
            }

            // Autenticar al usuario con su email y contraseña
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            // Si la autenticación es exitosa, generar el token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername()); // Usar getUsername(), ya que representa el email

            // Devolver el token en un TokenDto
            return ResponseEntity.ok(new TokenDto(jwtToken, jwtUtil.extractExpiration(jwtToken)));  // Usamos el método extractExpiration
        } catch (AuthenticationException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
            return ResponseEntity.status(401).build(); // Si la autenticación falla
        }
    }

    // Método de prueba para comprobar si el controlador está funcionando
    @GetMapping("/test")
    public String testAuthController() {
        return "AuthController is working!";
    }
}
