package org.friascop.appAP.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {
    public String codigoBarras;
    private Integer cantidad;
}
