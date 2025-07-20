package org.friascop.appAP.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    // Secreto para firmar los tokens (debe mantenerse seguro)

    @Value("${app.jwt.secret}")
    private String jwtSecr;


    private final String jwtSecret = "u4Vsj1CQX3HSkAOmNxqiy7Y6PqYTxj4jX0zAF+T2oJ4=";

    // Tiempo de expiración del token (en milisegundos)
    private final long jwtExpirationMs = 86400000;

    // Genera el token

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("rol", userDetails.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae el nombre de usuario del token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Valida el token
    public boolean validateToken(String token) {
        System.out.println("validando el token : " + token);
        try {
            Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

}
