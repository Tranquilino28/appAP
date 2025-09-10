package org.friascop.appAP.services.interfaces;

import org.friascop.appAP.dto.UsuarioPerfil_dto;
import org.friascop.appAP.dto.Usuario_dto;
import org.friascop.appAP.auxdb.modelos.Persona;
import org.friascop.appAP.auxdb.modelos.Usuario;
import org.friascop.appAP.genericServices.GenericService;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface InServ_Usuario extends GenericService<Usuario> {
    @Transactional
    Usuario crearUsuario(Usuario usuario, Persona persona);
// se esta usando la clase generica de servicios CRUD de esta clase

    @Transactional
    Optional<Usuario_dto> findById_dto(Long id);

    @Transactional
    Optional<Usuario> validarUsuario(String usuario);


    @Transactional
    boolean usuarioExists(String usuario, String password);


    Optional<Usuario> findByUsername(String username);

    Optional<UsuarioPerfil_dto> usuarioPerfil(String nombreUsuario);
}
