package org.friascop.appAP.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Producto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequest {

    private Long cliente_id;

    private Long empleado_id;

    private Long empresa_id;

    List<ProductoRequest> productos;

}
