package org.friascop.appAP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta_dto {

    private Long id;
    private BigDecimal valorTotal;
    private LocalDateTime fechaVenta;
    private String metodoPago;
    private String nombreCliente;
    private Empresa_dto empresa;
    private List<DetallesVenta_dto> detalles;

}
