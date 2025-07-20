package org.friascop.appAP.controllers;

import org.friascop.appAP.jwt.AuthRequest;
import org.friascop.appAP.jwt.AuthResponse;
import org.friascop.appAP.jwt.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.security.spec.InvalidKeySpecException;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class Controller_login {


    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        System.out.println("usuario y contrasdeña recibidos : "+request.getUsername());

        try {


            AuthResponse response = authService.login(request);

            // Retornamos 200 OK + token JWT
            return ResponseEntity.ok(response);
           // return ResponseEntity.ok("Login exitoso");
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
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
