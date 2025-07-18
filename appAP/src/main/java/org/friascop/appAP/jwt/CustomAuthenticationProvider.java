package org.friascop.appAP.jwt;

import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.services.InServ_Usuario;
import org.friascop.appAP.util.PassSecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private InServ_Usuario servUsuario;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();             // "frias"
        String passwordRaw = authentication.getCredentials().toString();  // "frias"

        Usuario usuario = servUsuario.validarUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String salt = usuario.getHashsalt();                        // "gK93J1..."
        String expectedHash = usuario.getHashpassword();        // "ae3b9d..."

        String generatedHash = null;
        try {
            generatedHash = PassSecure.hashPassword(passwordRaw, salt);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        if (!generatedHash.equals(expectedHash)) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        return new UsernamePasswordAuthenticationToken(username, passwordRaw, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

