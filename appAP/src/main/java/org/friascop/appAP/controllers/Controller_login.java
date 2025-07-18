package org.friascop.appAP.controllers;

import org.friascop.appAP.dto.LoginRequest;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.jwt.JwtUtils;
import org.friascop.appAP.services.InServ_Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class Controller_login {


    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    public Controller_login(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest usuario) {

        System.out.println("usuario y contrasdeña recibidos : "+usuario.getUsername());

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
            );

            // Si es exitoso, genera el token JWT
            String token = jwtUtils.generateJwtToken(auth);

            System.out.println("el token se genero correctamente : \n"+token);
            // Puedes devolver solo el token o un objeto
            return ResponseEntity.ok().body(Collections.singletonMap("token", token));


           // return ResponseEntity.ok("Login exitoso");
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }


/*
    final private InServ_Usuario servUsuario;

    public Controller_login(InServ_Usuario servUsuario) {
        this.servUsuario = servUsuario;
    }

    @Transactional(readOnly = true)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest usuario) {
       if( servUsuario.usuarioExists(usuario.getUsername(), usuario.getPassword())){
           return ResponseEntity.ok().build();
        }else{
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario y contraseña incorrecto!");
       }

    }
*/






}
