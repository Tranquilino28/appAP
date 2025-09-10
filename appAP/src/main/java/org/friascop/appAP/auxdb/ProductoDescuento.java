package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;

@Entity
@Table(name = "productosdescuentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ProductoDescuentoId.class)
public class ProductoDescuento {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "desc_id")
    private Descuento descuento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}
