package org.friascop.appAP.mapper;

import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;
import org.friascop.appAP.dto.Producto_dto;

public class MapperProducto {

        public static Producto_dto toDto(Producto p) {
            if (p == null) {
                return null;
            }


                return new Producto_dto(
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getCodigo(),             // en el DTO se llama codigoBarra
                        p.getPrecio(),
                        p.getCategoria(),
                        p.getMedida(),
                        p.getStockDisponible(),
                        p.getEmpresa() != null ? p.getEmpresa().getId() : null // solo ID de Empresa
                );
        }

        public static Producto toEntity(Producto_dto dto) {
            if (dto == null) {
                return null;
            }

            Producto producto = new Producto();
            producto.setId(dto.getId());
            producto.setNombre(dto.getNombre());
            producto.setDescripcion(dto.getDescripcion());
            producto.setCodigo(dto.getCodigoBarra()); // mapeo campo distinto
            producto.setPrecio(dto.getPrecio());
            producto.setCategoria(dto.getCategoria());
            producto.setMedida(dto.getMedida());
            producto.setStockDisponible(dto.getStockDisponible());

            // Relación con Empresa (objeto fantasma con solo ID)
            if (dto.getEmpresa_id() != null) {
                Empresa empresa = new Empresa();

                empresa.setId(dto.getEmpresa_id()); // ⚠️ aquí también depende si usas id o nit
                producto.setEmpresa(empresa);
            }

            return producto;
        }


}
