package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;

@Entity
@Table(name = "productosproveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ProductoProveedorId.class)
public class ProductoProveedor {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "prov_id")
    private Proveedor proveedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}

