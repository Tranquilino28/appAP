package org.friascop.appAP.jwt;

import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.repositories.InRepositorio_Usuario;
import org.friascop.appAP.util.PassSecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private InRepositorio_Usuario usuarioRepo;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Realiza el login: valida usuario y contraseña + genera JWT
     */

    public AuthResponse login(AuthRequest request) throws InvalidKeySpecException {

        // Buscamos el usuario en la base de datos
        Usuario usuario = usuarioRepo.findByNombreUsuario(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Verificación de contraseña con salt hash
        if (!PassSecure.verifyPassword(request.getPassword(), usuario.getHashsalt(), usuario.getHashpassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        System.out.println("el rol de esto es "+usuario.getRol());

        // Creamos el objeto User que Spring entiende
        UserDetails userDetails = new User(usuario.getNombreUsuario(), usuario.getHashpassword(),
                List.of(new SimpleGrantedAuthority(usuario.getRol())));

        // Generamos el JWT
        String token = jwtUtils.generateToken(userDetails);

        return new AuthResponse(token);
    }
}

