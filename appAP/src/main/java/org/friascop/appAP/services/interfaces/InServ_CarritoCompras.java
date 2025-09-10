package org.friascop.appAP.services.interfaces;

import org.friascop.appAP.auxdb.CarritoCompras;
import org.friascop.appAP.dto.CarritoCompras_dto;
import org.friascop.appAP.genericServices.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface InServ_CarritoCompras extends GenericService<CarritoCompras> {
    @Transactional
    Optional<CarritoCompras_dto> findById_dto(Long id);

}