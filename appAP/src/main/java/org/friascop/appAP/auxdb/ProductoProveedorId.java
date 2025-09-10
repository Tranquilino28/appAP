package org.friascop.appAP.auxdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoProveedorId implements Serializable {
    private Long producto;
    private Long proveedor;
    private Empresa empresa;
}
