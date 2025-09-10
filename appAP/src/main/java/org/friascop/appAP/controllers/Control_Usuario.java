package org.friascop.appAP.controllers;

import org.friascop.appAP.auxdb.modelos.Usuario;
import org.friascop.appAP.dto.UsuarioPerfil_dto;
import org.friascop.appAP.dto.Usuario_dto;
import org.friascop.appAP.services.impl.Impl_Serv_Usuario;
import org.friascop.appAP.services.interfaces.InServ_Persona;
import org.friascop.appAP.services.interfaces.InServ_Usuario;
import org.friascop.appAP.util.PassSecure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class Control_Usuario {
    /**
     *
     */
    final private InServ_Usuario servUsuario;
    final private InServ_Persona servPersona;
    private final Impl_Serv_Usuario impl_Serv_Usuario;


    public Control_Usuario(InServ_Usuario servUsuario, InServ_Persona servPersona, Impl_Serv_Usuario impl_Serv_Usuario) {
        this.servUsuario = servUsuario;
        this.servPersona = servPersona;
        this.impl_Serv_Usuario = impl_Serv_Usuario;
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
    @PostMapping("/register/newUser")
    public ResponseEntity<Usuario> createNewUser(@RequestBody Usuario usuario) throws InvalidKeySpecException {




        System.out.println("nombre : "+usuario.getNombreUsuario()
                + "\nsalt password: "+ usuario.getHashpassword());

        PassSecure.hashPassword(usuario.getHashpassword(), PassSecure.generateSalt());
        servPersona.save(usuario.getPersona());
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

    /**
     *
     * @param
     * @return
     */
    //Eliminala persona buscadola por el id
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfil_dto> getPerfil(Authentication authentication) {
        String nombreUsuario = authentication.getName();

        return servUsuario.usuarioPerfil(nombreUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
