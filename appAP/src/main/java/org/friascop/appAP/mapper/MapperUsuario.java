package org.friascop.appAP.mapper;

import org.friascop.appAP.auxdb.modelos.Usuario;
import org.friascop.appAP.dto.Usuario_dto;

public class MapperUsuario {


    public static Usuario_dto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new Usuario_dto(
                usuario.getId(),
                usuario.getCodigo(),
                usuario.getNombreUsuario(),
                usuario.getRol(),
                usuario.getPersona() != null ? usuario.getPersona().getId() : null,
                usuario.getEmpresa() != null ? usuario.getEmpresa().getId() : null
        );
    }

}
