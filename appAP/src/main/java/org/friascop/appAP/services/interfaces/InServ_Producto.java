package org.friascop.appAP.services.interfaces;

import org.friascop.appAP.auxdb.modelos.Producto;
import org.friascop.appAP.dto.Producto_dto;
import org.friascop.appAP.genericServices.GenericService;
import org.friascop.appAP.request.ProductoRequest;

import java.util.List;
import java.util.Optional;

public interface InServ_Producto extends GenericService<Producto> {

// se esta usando la clase generica de servicios CRUD de esta clase

    Optional<Producto_dto> findByCodigoBarrasGreaterThanZero(String codigo_de_barras);

    List<Producto_dto> findAllProductosGreaterThanZero();

    List<Producto_dto> findByCodigoBarrasOrNombreGreaterThanZero(String texto);


    Optional<Producto_dto> findByCodigoBarras(String codigo_de_barras);

    Optional<Producto_dto> saveOrUbdate(Producto_dto request);
}
