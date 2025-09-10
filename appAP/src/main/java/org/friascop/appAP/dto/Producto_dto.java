package org.friascop.appAP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto_dto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String codigoBarra;
    private BigDecimal precio;
    private String categoria;
    private String medida;
    private Integer stockDisponible;
    private Long empresa_id;
}
