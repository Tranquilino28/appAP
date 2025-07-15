package org.friascop.appAP.repositories;

import org.friascop.appAP.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InRepositorio_Usuario extends CrudRepository<Usuario,Long> {


    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
