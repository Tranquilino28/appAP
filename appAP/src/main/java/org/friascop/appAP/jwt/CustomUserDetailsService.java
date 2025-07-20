package org.friascop.appAP.jwt;

import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.repositories.InRepositorio_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private InRepositorio_Usuario usuarioRepository;


    // Spring Security llama este método automáticamente para autenticar

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscamos el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Devolvemos un objeto User que Spring Security entiende
        return new User(usuario.getNombreUsuario(), usuario.getHashpassword(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol())));
    }
}
