package org.friascop.appAP.auxdb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;
import org.friascop.appAP.auxdb.modelos.Venta;

import java.math.BigDecimal;

@Entity
@Table(name = "detallesventa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;


}

