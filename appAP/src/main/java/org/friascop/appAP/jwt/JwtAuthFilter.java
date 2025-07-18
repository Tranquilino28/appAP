package org.friascop.appAP.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Leer la cabecera Authorization
        String header = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // Verificamos que comience con "Bearer "
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7); // Extraemos el token
            username = jwtUtils.getUsernameFromJwtToken(token); // Sacamos el usuario del token
        }

        // Si tenemos usuario y aún no está autenticado en esta petición
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargamos detalles del usuario (roles, estado, etc.)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validamos el token
            if (jwtUtils.validateJwtToken(token)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Registramos la autenticación en el contexto de Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuamos con el siguiente filtro
        filterChain.doFilter(request, response);
    }


}
