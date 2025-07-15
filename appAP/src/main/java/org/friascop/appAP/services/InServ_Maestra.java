package org.friascop.appAP.services;

import org.friascop.appAP.entities.Maestra;
import org.friascop.appAP.genericServices.GenericService;

import java.util.List;

public interface InServ_Maestra extends GenericService<Maestra> {
    List<Maestra> saveAll(List<Maestra> maestras);
// se esta usando la clase generica de servicios CRUD de esta clase
}
