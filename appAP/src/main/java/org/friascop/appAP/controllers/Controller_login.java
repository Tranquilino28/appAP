package org.friascop.appAP.controllers;

import org.friascop.appAP.dto.LoginRequest;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.services.InServ_Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class Controller_login {

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







}
