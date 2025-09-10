package org.friascop.appAP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cliente_dto {

    private Long id;

    private Persona_dto persona;
    private Empresa_dto empresa;

}
