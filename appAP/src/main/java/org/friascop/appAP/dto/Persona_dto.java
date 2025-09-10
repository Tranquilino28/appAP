package org.friascop.appAP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Persona_dto {

    private Long id;

    private String identificacion;

    private String nombre;

    private String apellido;

    private String direccion;

    private String tipoIdentificacion;

    private String tipoSexo;

    private String tipoEstado;


}
