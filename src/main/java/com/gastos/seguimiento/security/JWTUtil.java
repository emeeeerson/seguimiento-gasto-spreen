package com.gastos.seguimiento.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {

    @Autowired
    private JWTConfig jwtConfig;

    // Generar un token JWT para un usuario específico usando el email
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Usa el email como el subject del token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationMs()))
                .signWith(jwtConfig.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener el email (subject) del token JWT
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject); // Devuelve el email, que ahora es el subject
    }

    // Validar el token JWT comparando el email y verificando si ha expirado
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    // Método para extraer cualquier claim del token JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todos los claims del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraer la fecha de expiración del token JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
