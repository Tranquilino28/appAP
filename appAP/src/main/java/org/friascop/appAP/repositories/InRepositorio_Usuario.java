package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.modelos.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InRepositorio_Usuario extends CrudRepository<Usuario,Long> {


    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
