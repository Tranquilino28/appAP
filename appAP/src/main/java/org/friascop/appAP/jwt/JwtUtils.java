package org.friascop.appAP.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    // Clave secreta que se usará para firmar el token
    // Debe ser de al menos 256 bits (32 caracteres si es texto)
    private final String jwtSecret = "JWT_SECRET-frias=@. G7p!mQ#2zV$9tK@xE4sN^5bW0hY*1rJ&dF8uL6cA\n";

    // Tiempo de expiración en milisegundos (1 hora)
    private final long jwtExpirationMs = 3600000;

    // Devuelve la clave como objeto Key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Generar el token a partir del nombre de usuario
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", roles) // 👈 aquí van los roles
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();


        /* return Jwts.builder()
                .setSubject(username) // usuario
                .setIssuedAt(new Date()) // fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // expiración
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // algoritmo de firma
                .compact();*/
    }


    // Obtener usuario (subject) del token
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validar que el token sea correcto y no esté expirado
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken)
                    .getBody()
                    .getSubject();
            return true;
        } catch (JwtException e) {
            // Cualquier excepción significa que el token no es válido
            System.out.println("Token inválido: " + e.getMessage());
        }
        return false;
    }


}
