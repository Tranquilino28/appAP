package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.CarritoCompras;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InRepositorio_CarritoCompras extends CrudRepository<CarritoCompras,Long> {



}
