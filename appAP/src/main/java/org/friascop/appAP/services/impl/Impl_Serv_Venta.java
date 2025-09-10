package org.friascop.appAP.services.impl;

import org.friascop.appAP.auxdb.Cliente;
import org.friascop.appAP.auxdb.DetalleVenta;
import org.friascop.appAP.auxdb.modelos.Venta;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Maestra;
import org.friascop.appAP.auxdb.modelos.Producto;
import org.friascop.appAP.dto.Venta_dto;
import org.friascop.appAP.exceptions.StockInsuficienteException;
import org.friascop.appAP.mapper.MapperVenta;
import org.friascop.appAP.repositories.*;
import org.friascop.appAP.request.VentaRequest;
import org.friascop.appAP.request.ProductoRequest;
import org.friascop.appAP.services.interfaces.InServ_Venta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Impl_Serv_Venta implements InServ_Venta {

    // Se IMPLEMENTA todas las funciones del servicio CRUD DE usuario

    final private InRepositorio_Producto repoProducto;
    final private InRepositorio_Venta repoVenta;
    final private InRepositorio_Cliente repoCliente;
    final private InRepositorio_DetalleVenta repoDetalleVenta;
    final private InRepositorio_Empresa repoEmpresa;

    public Impl_Serv_Venta(InRepositorio_Producto repoProducto, InRepositorio_Venta repoVenta, InRepositorio_Cliente repoCliente, InRepositorio_DetalleVenta repoDetalleVenta, InRepositorio_Empresa repoEmpresa) {
        this.repoProducto = repoProducto;
        this.repoVenta = repoVenta;
        this.repoCliente = repoCliente;
        this.repoDetalleVenta = repoDetalleVenta;

        this.repoEmpresa = repoEmpresa;
    }


    @Override
    public List<Venta> findAll() {
        return List.of();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Venta save(Venta entity) {
        return null;
    }

    @Override
    public Optional<Venta> deleteById(Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Venta_dto createVenta(VentaRequest request) {
        // ✅ Validación para evitar el NullPointerException
        if (request.getProductos() == null) {
            throw new RuntimeException("La lista de productos no puede ser nula.");
        }

        Cliente cliente = repoCliente.findById(request.getCliente_id())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Optional<Empresa> empresa = repoEmpresa.findById(request.getEmpresa_id());
        // Traer productos
        List<String> codigos = request.getProductos().stream()
                .map(ProductoRequest::getCodigoBarras)
                .toList();

        Map<String, Producto> productoMap = repoProducto.findAllByCodigoIn(codigos)
                .stream().collect(Collectors.toMap(Producto::getCodigo, p -> p));

        Venta venta = Venta.builder()
                .fechaVenta(LocalDateTime.now())
                .valorTotal(BigDecimal.ZERO)
                .empresa(cliente.getEmpresa())
                .cliente(cliente)
                .metodo_de_pago(new Maestra(1L)) // ejemplo: efectivo
                .build();

        BigDecimal total = BigDecimal.ZERO;
        List<DetalleVenta> detalles = new ArrayList<>();

       /* for (ProductoRequest pr : request.getProductos()) {
            Producto producto = productoMap.get(pr.getCodigoBarras());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + pr.getCodigoBarras());
            }

            BigDecimal precioUnitario = producto.getPrecio();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(pr.getCantidad()));
            total = total.add(subtotal);

            detalles.add(DetalleVenta.builder()
                    .venta(venta)
                    .producto(producto)
                    .cantidad(pr.getCantidad())
                    .precioUnitario(precioUnitario)
                    .empresa(empresa.get())
                    .build()
            );
        }*/
        for (ProductoRequest pr : request.getProductos()) {
            Producto producto = productoMap.get(pr.getCodigoBarras());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + pr.getCodigoBarras());
            }

            // 🟢 LÓGICA AGREGADA AQUÍ 🟢
            // 1. Validar que hay suficiente stock
            if (producto.getStockDisponible() < pr.getCantidad()) {
                throw new StockInsuficienteException("No hay suficiente stock para el producto: " + producto.getNombre());
            }

            // 2. Restar la cantidad vendida del stock
            producto.setStockDisponible(producto.getStockDisponible() - pr.getCantidad());
            // 🟢 FIN DE LÓGICA AGREGADA 🟢

            BigDecimal precioUnitario = producto.getPrecio();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(pr.getCantidad()));
            total = total.add(subtotal);

            detalles.add(DetalleVenta.builder()
                    .venta(venta)
                    .producto(producto)
                    .cantidad(pr.getCantidad())
                    .precioUnitario(precioUnitario)
                    .empresa(empresa.get())
                    .build()
            );
        }
        venta.setValorTotal(total);
        venta.setDetalles(detalles);

        // Guardar todo en cascada
        Venta saved = repoVenta.save(venta);

        // Convertir a DTO limpio
        return MapperVenta.toDto(saved);
    }

}
