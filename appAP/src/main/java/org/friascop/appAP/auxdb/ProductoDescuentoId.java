package org.friascop.appAP.auxdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDescuentoId implements Serializable {
    private Long producto;
    private Long descuento;
    private Empresa empresa;

}

