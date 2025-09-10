package org.friascop.appAP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Producto;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallesVenta_dto {

  private Long id;
  private Integer cantidad;
  private BigDecimal precioUnitario;
  private Producto_dto producto;
  private Empresa_dto empresa;


}
