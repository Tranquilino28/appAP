package org.friascop.appAP.mapper;

import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Maestra;
import org.friascop.appAP.dto.Empresa_dto;

import java.util.Base64;

public class MapperEmpresa {

    public static Empresa_dto toDto(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        return new Empresa_dto(
                empresa.getId(),
                empresa.getNit(),
                empresa.getNombre(),
                empresa.getDescripcion(),
                empresa.getDireccion(),
                empresa.getTelefono(),
                empresa.getEmail(),
                empresa.getLogo() != null ? Base64.getEncoder().encodeToString(empresa.getLogo()) : null,
                empresa.getTipo_de_Categoria() != null ? empresa.getTipo_de_Categoria().getId() : null,
                empresa.getTipo_de_empresa() != null ? empresa.getTipo_de_empresa().getId() : null
        );
    }
    public static Empresa_dto toDtoElemental(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        Empresa_dto empresaDto = new Empresa_dto();
        empresaDto.setId(empresa.getId());
        empresaDto.setNombre(empresa.getNombre());
        return empresaDto;
    }


    public static Empresa toEntity(Empresa_dto dto) {
        if (dto == null) {
            return null;
        }


            return new Empresa(
                    dto.getId(),
                    dto.getNit(),
                    dto.getNombre(),
                    dto.getDescripcion(),
                    dto.getDireccion(),
                    dto.getTelefono(),
                    dto.getEmail(),
                    dto.getLogoBase64() != null && !dto.getLogoBase64().isEmpty()
                            ? Base64.getDecoder().decode(dto.getLogoBase64())
                            : null,
                    dto.getCategoriaId() != null ? new Maestra(dto.getCategoriaId()) : null,
                    dto.getEstadoId() != null ? new Maestra(dto.getEstadoId()) : null
            );

    }

}
