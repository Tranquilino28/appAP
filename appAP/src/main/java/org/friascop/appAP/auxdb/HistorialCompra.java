package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;

@Entity
@Table(name = "historialcompras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedi_id")
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}
