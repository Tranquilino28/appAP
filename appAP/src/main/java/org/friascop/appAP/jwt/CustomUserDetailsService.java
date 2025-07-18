package org.friascop.appAP.jwt;

import io.jsonwebtoken.lang.Collections;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.repositories.InRepositorio_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private InRepositorio_Usuario usuarioRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // Busca el usuario en tu base de datos
            Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

            // Devuelve un UserDetails con username, password y roles
            return new org.springframework.security.core.userdetails.User(
                    usuario.getNombreUsuario(),
                    usuario.getHashpassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
            );
        }
}
