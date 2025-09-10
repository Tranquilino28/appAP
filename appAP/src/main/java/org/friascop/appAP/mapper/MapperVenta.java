package org.friascop.appAP.mapper;

import org.friascop.appAP.auxdb.modelos.Venta;
import org.friascop.appAP.dto.Venta_dto;

import java.util.stream.Collectors;

public class MapperVenta {

    public static Venta_dto toDto(Venta venta) {
        if (venta == null) return null;
        return Venta_dto.builder()
                .id(venta.getId())
                .valorTotal(venta.getValorTotal())
                .fechaVenta(venta.getFechaVenta())
                .metodoPago(venta.getMetodo_de_pago() != null ? venta.getMetodo_de_pago().getNombreLargo() : null)
                .nombreCliente(venta.getCliente() != null ? venta.getCliente().getPersona().getNombre() : null)
                .empresa(venta.getEmpresa() != null ? MapperEmpresa.toDtoElemental(venta.getEmpresa()) : null)
                .detalles(
                        venta.getDetalles().stream()
                                .map(MapperDetalles::toDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

