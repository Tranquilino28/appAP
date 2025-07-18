package org.friascop.appAP.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Para usar @PreAuthorize
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF porque es REST
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No usar sesiones
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Permite login sin token
                        .requestMatchers("/api/user/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").hasRole("ADMIN")// Permite login sin token
                        .anyRequest().authenticated() // Todo lo demás requiere autenticación

                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);;

        // Aquí luego se agregará el filtro JWT
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenticationProvider)
                .build();
    }


}
/**
 * package org.friascop.appAP.config;
 *
 * import org.friascop.appAP.jwt.CustomUserDetailsService;
 * import org.friascop.appAP.jwt.JwtAuthFilter;
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.AuthenticationProvider;
 * import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
 * import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 * import org.springframework.security.config.http.SessionCreationPolicy;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.security.web.SecurityFilterChain;
 * import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 *
 * @Configuration
 * @EnableWebSecurity
 * public class SecurityConfig {
 *
 *     private final CustomUserDetailsService userDetailsService;
 *     private final JwtAuthFilter jwtAuthFilter;
 *
 *     // Inyectamos las dependencias que Spring ya gestiona (gracias a @Component y @Service)
 *     public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthFilter jwtAuthFilter) {
 *         this.userDetailsService = userDetailsService;
 *         this.jwtAuthFilter = jwtAuthFilter;
 *     }
 *
 *     @Bean
 *     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 *         return http
 *                 .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
 *                 .authorizeHttpRequests(auth -> auth
 *                         // Define aquí tus rutas públicas (que no necesitan token)
 *                         .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
 *                         // Todas las demás rutas requieren autenticación
 *                         .anyRequest().authenticated()
 *                 )
 *                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sesión sin estado
 *                 .authenticationProvider(authenticationProvider())
 *                 // Usamos la INSTANCIA INYECTADA del filtro, no una nueva
 *                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
 *                 .build();
 *     }
 *
 *     @Bean
 *     public PasswordEncoder passwordEncoder() {
 *         return new BCryptPasswordEncoder();
 *     }
 *
 *     @Bean
 *     public AuthenticationProvider authenticationProvider() {
 *         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 *         authProvider.setUserDetailsService(userDetailsService);
 *         authProvider.setPasswordEncoder(passwordEncoder());
 *         return authProvider;
 *     }
 *
 *     @Bean
 *     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
 *         return config.getAuthenticationManager();
 *     }
 * }
 */