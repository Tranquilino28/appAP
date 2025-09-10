package org.friascop.appAP.mapper;

import org.friascop.appAP.auxdb.Cliente;
import org.friascop.appAP.auxdb.DetalleVenta;
import org.friascop.appAP.dto.Cliente_dto;
import org.friascop.appAP.dto.DetallesVenta_dto;


public class MapperDetalles {
    public static DetallesVenta_dto toDto(DetalleVenta detallesVenta) {
        if (detallesVenta == null) {
            return null;
        }

        return new DetallesVenta_dto(
                detallesVenta.getId(),
                detallesVenta.getCantidad(),
                detallesVenta.getPrecioUnitario(),
                detallesVenta.getProducto() != null ? MapperProducto.toDto(detallesVenta.getProducto()) : null,
                detallesVenta.getEmpresa() != null ? MapperEmpresa.toDto(detallesVenta.getEmpresa()) : null
        );
    }
}
