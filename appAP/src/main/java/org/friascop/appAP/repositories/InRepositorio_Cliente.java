package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InRepositorio_Cliente extends CrudRepository<Cliente,Long> {


}
