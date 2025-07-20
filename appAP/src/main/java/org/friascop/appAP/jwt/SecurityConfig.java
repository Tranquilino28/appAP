package org.friascop.appAP.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitamos CSRF para APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // login público
                        .requestMatchers("/api/user/register/newUser").permitAll() //registro de usuario publico

                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // solo admin
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN") // user o admin
                        .anyRequest().authenticated()) // todo lo demás requiere login
                .userDetailsService(userDetailsService) // nuestro servicio personalizado
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // filtro antes de login
                .build();
    }
}

