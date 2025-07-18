package org.friascop.appAP.repositories;

import org.friascop.appAP.entities.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface InRepositorio_Persona extends CrudRepository<Persona,Long> {



}
