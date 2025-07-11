package org.friascop.appAP.services;

import org.friascop.appAP.entities.Persona;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.genericServices.GenericService;
import org.springframework.transaction.annotation.Transactional;

public interface InServ_Usuario extends GenericService<Usuario> {
    @Transactional
    Usuario crearUsuario(Usuario usuario, Persona persona);
// se esta usando la clase generica de servicios CRUD de esta clase
}
