package org.friascop.appAP.services.interfaces;

import org.friascop.appAP.auxdb.modelos.Venta;
import org.friascop.appAP.dto.Venta_dto;
import org.friascop.appAP.genericServices.GenericService;
import org.friascop.appAP.request.VentaRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InServ_Venta extends GenericService<Venta> {

    @Transactional
    Venta_dto createVenta(VentaRequest request);

    @Transactional
    List<Venta_dto> getAllVenta();
}
