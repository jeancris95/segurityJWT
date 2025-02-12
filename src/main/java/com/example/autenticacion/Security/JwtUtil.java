package com.example.autenticacion.Security;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static Key getSigningKey() {  // 🔹 Método para acceder a la clave
        return key;
    }

    // Método para generar un token JWT
    public String generarToken(String username, List<String> roles) {
        // Asegurarte de que los roles tienen el prefijo ROLE_
        List<String> rolesConPrefijo = roles.stream()
                .map(role -> "ROLE_" + role.toUpperCase())  // Asegura que todos los roles tienen el prefijo "ROLE_"
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("roles", rolesConPrefijo)  // Establece los roles con el prefijo correcto
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

}
