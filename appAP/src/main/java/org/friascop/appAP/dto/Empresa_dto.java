package org.friascop.appAP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa_dto {
        private Long id;
        private String nit;
        private String nombre;
        private String descripcion;
        private String direccion;
        private String telefono;
        private String email;
        private String logoBase64;
        private Long categoriaId;
        private Long estadoId;

}
