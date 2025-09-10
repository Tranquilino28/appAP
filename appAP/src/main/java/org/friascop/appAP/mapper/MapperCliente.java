package org.friascop.appAP.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.Cliente;
import org.friascop.appAP.dto.Cliente_dto;
import org.friascop.appAP.dto.Empresa_dto;
import org.friascop.appAP.dto.Persona_dto;


public class MapperCliente {
    public static Cliente_dto toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return new Cliente_dto(
                cliente.getId(),
                cliente.getPersona() != null ? MapperPersona.toDto(cliente.getPersona()) : null,
                cliente.getEmpresa() != null ? MapperEmpresa.toDto(cliente.getEmpresa()) : null
        );
    }
}
