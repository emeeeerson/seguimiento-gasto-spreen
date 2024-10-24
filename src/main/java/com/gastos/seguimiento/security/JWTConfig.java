package com.gastos.seguimiento.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JWTConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    // Método para obtener la clave secreta que se utilizará para firmar los JWTs
    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);  // Decodifica la clave secreta almacenada en Base64
        return Keys.hmacShaKeyFor(keyBytes);               // Genera la clave secreta HMAC con la longitud adecuada
    }

    public String getSecret() {
        return secret;
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}
