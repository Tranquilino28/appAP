package org.friascop.appAP.services;

import org.friascop.appAP.dto.Usuario_dto;
import org.friascop.appAP.entities.Persona;
import org.friascop.appAP.entities.Usuario;
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
}
