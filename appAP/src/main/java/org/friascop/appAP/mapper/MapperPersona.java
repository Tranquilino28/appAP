package org.friascop.appAP.mapper;

import org.friascop.appAP.auxdb.modelos.Persona;
import org.friascop.appAP.dto.Persona_dto;

public class MapperPersona {

    public static Persona_dto toDto(Persona p) {
        if (p == null) {
            return null;
        }

        Persona_dto personaDto =new Persona_dto(
                p.getId()
                , p.getIdentificacion()
                , p.getNombre()
                , p.getApellido()
                , p.getDireccion()
                , p.getTipo_de_identificacion() != null ? p.getTipo_de_identificacion().getNombreLargo() : null
                , p.getTipo_de_sexo() != null ? p.getTipo_de_sexo().getNombreLargo() : null
                , p.getEstado() != null ? p.getEstado().getNombreLargo() : null

        );
        return personaDto;
    }
/*
    public static Persona toEntity(Persona_dto dto) {
        if (dto == null) {
            return null;
        }

        Persona persona = new Persona();
        persona.setId(dto.getId());
        persona.setIdentificacion(dto.getIdentificacion());
        persona.setNombre(dto.getNombre());
        persona.setApellido(dto.getApellido());
        persona.setDireccion(dto.getDireccion());

        // Relaciones con Maestra (creamos objeto fantasma con solo nombre)
        if (dto.getTipoIdentificacion() != null) {
            persona.setTipoIdentificacion(new Maestra(dto.getTipoIdentificacion()));
        }
        if (dto.getTipoSexo() != null) {
            persona.setTipoSexo(new Maestra(dto.getTipoSexo()));
        }
        if (dto.getTipoEstado() != null) {
            persona.setTipoEstado(new Maestra(dto.getTipoEstado()));
        }

        return persona;
    }*/
}

