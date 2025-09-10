package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;

import java.math.BigDecimal;

@Entity
@Table(name = "detallespedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedi_id")
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;

}

