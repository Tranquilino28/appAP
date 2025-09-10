package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.modelos.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InRepositorio_Empresa extends CrudRepository<Empresa,Long> {



}
