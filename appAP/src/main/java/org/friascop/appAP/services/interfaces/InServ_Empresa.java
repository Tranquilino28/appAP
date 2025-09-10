package org.friascop.appAP.services.interfaces;

import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.genericServices.GenericService;

import java.util.List;

public interface InServ_Empresa extends GenericService<Empresa> {
    List<Empresa> saveAll(List<Empresa> empresa);
// se esta usando la clase generica de servicios CRUD de esta clase
}
