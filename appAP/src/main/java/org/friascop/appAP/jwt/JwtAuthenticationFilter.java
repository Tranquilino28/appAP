package org.friascop.appAP.jwt;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        final String authHeader = request.getHeader("Authorization");

        // 1. Validamos que el header comience con "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // quitamos "Bearer "

            // 2. Validamos el token antes de hacer nada más
            if (jwtUtils.validateToken(jwt)) {
                // 3. Extraemos username y rol desde el token
                String username = jwtUtils.extractUsername(jwt);
                String rol = jwtUtils.extractRol(jwt);

                // 4. Si no hay una autenticación activa en el contexto
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 5. Creamos autoridad desde el rol del token
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(rol));

                    // 6. Creamos autenticación con username y autoridad (sin password ni detalles)
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    // 7. Registramos la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        // 8. Continuamos con el resto del pipeline
        filterChain.doFilter(request, response);
    }
}
/**
 * Se ejecuta en cada request para validar el JWT

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException, java.io.IOException {

    final String authHeader = request.getHeader("Authorization");

    // Validamos que el header comience con "Bearer "
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String jwt = authHeader.substring(7); // quitamos "Bearer "
        String username = jwtUtils.extractUsername(jwt);
        String rol = jwtUtils.extractRol(jwt);

        // Si hay un username y no hay autenticación previa
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validamos el token
            if (jwtUtils.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Establecemos el usuario autenticado en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    }

    filterChain.doFilter(request, response); // continúa con la cadena
}
}
*/
