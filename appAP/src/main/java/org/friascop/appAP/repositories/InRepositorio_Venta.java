package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.modelos.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InRepositorio_Venta extends CrudRepository<Venta,Long> {

    @Query("SELECT v FROM Venta v LEFT JOIN FETCH v.detalles d LEFT JOIN FETCH d.producto WHERE v.id = :id")
    Optional<Venta> findByIdWithDetalles(@Param("id") Long id);
}
