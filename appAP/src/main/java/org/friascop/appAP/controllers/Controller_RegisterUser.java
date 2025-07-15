package org.friascop.appAP.controllers;

import org.friascop.appAP.dto.Usuario_dto;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.services.InServ_Usuario;
import org.friascop.appAP.util.PassSecure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class Controller_RegisterUser {
    /**
     *
     */
    final private InServ_Usuario servUsuario;

    public Controller_RegisterUser(InServ_Usuario servUsuario) {
        this.servUsuario = servUsuario;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){

        return ResponseEntity.ok(servUsuario.findAll());
    }

    /**
     *
     * @param id
     * @return usuario con la informacion
     */
    //muestra todos los detallesd de la persona que se quiere mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Usuario_dto> details(@PathVariable Long id) {

        Optional<Usuario_dto> usuarioOptional = servUsuario.findById_dto(id);

        if (usuarioOptional.isPresent()) {

            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    /**
     *
     * @param usuario
     * @return usuario con los detalles
     * @throws InvalidKeySpecException
     */
    //crea la persona pasandole el objeto de la perosna que se creo en otra clase

    @PostMapping("/register")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) throws InvalidKeySpecException {

        System.out.println("nombre : "+usuario.getNombreUsuario()
        + "\nsalt password: "+ usuario.getHashpassword());

        PassSecure.hashPassword(usuario.getHashpassword(), PassSecure.generateSalt());
       Usuario usuarioDb = servUsuario.save(usuario);

        return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
    }



    //Actualiza la persona buscadolo por su id y modificando con los cambios que se pasan
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> ubdate(@RequestBody Usuario usuario, @PathVariable Long id) {

        Optional<Usuario> usuarioOptional = servUsuario.findById(id);

        if (usuarioOptional.isPresent()) {

            Usuario usuarioDB = usuarioOptional.orElseThrow();

            usuarioDB.setCodigo(usuario.getCodigo());
            usuarioDB.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDB.setHashsalt(usuario.getHashsalt());
            usuarioDB.setHashpassword(usuario.getHashpassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(servUsuario.save(usuarioDB));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @return
     */
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
