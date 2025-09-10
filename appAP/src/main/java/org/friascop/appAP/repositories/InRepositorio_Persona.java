package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.modelos.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InRepositorio_Persona extends CrudRepository<Persona,Long> {



}
