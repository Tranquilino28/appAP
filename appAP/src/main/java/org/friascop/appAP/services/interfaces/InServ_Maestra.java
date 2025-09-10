package org.friascop.appAP.services.interfaces;

import org.friascop.appAP.auxdb.modelos.Maestra;
import org.friascop.appAP.dto.Maestra_dto;
import org.friascop.appAP.genericServices.GenericService;

import java.util.List;

public interface InServ_Maestra extends GenericService<Maestra> {

    List<Maestra_dto> listarDependientes(String nombrePadre);

    List<Maestra> saveAll(List<Maestra> maestras);

}
