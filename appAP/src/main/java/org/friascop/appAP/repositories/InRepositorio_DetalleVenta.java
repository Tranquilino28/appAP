package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.DetalleVenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InRepositorio_DetalleVenta extends CrudRepository<DetalleVenta,Long> {


}
