package org.friascop.appAP.controllers;

import org.friascop.appAP.entities.Maestra;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.services.InServ_Maestra;
import org.friascop.appAP.services.InServ_Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registroUsuario")
public class Controller_Registro_Usuario {

    final private InServ_Usuario servUsuario;

    public Controller_Registro_Usuario(InServ_Usuario servUsuario) {
        this.servUsuario = servUsuario;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> list(){

        return ResponseEntity.ok(servUsuario.findAll());
    }

    //muestra todos los detallesd de la persona que se quiere mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> details(@PathVariable Long id) {

        Optional<Usuario> usuarioOptional = servUsuario.findById(id);

        if (usuarioOptional.isPresent()) {

            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    //crea la persona pasandole el objeto de la perosna que se creo en otra clase
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {

        Usuario usuarioDb =servUsuario.save(usuario);

        return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
    }

    //Actualiza la persona buscadolo por su id y modificando con los cambios que se pasan
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> ubdate(@RequestBody Usuario usuario, @PathVariable Long id) {

        Optional<Usuario> usuarioOptional = servUsuario.findById(id);

        if (usuarioOptional.isPresent()) {

            Usuario usuarioDB = usuarioOptional.orElseThrow();


            usuarioDB.setUsua_codigo(usuario.getUsua_codigo());
            usuarioDB.setUsua_usuario(usuario.getUsua_usuario());
            usuarioDB.setHash_salt(usuario.getHash_salt());
            usuarioDB.setHash_password(usuario.getHash_password());


            return ResponseEntity.status(HttpStatus.CREATED).body(servUsuario.save(usuarioDB));
        }

        return ResponseEntity.notFound().build();
    }

    //Eliminala persona buscadola por el id
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id) {

        Optional<Usuario> usuarioOptional = servUsuario.findById(id);

        if (usuarioOptional.isPresent()) {

            Usuario deleteUsuario = usuarioOptional.orElseThrow();

            return ResponseEntity.status(HttpStatus.OK).body(deleteUsuario);
        }
        return ResponseEntity.notFound().build();

    }


}
