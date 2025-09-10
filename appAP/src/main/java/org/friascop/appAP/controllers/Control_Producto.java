package org.friascop.appAP.controllers;

import org.friascop.appAP.auxdb.modelos.Producto;
import org.friascop.appAP.dto.Producto_dto;
import org.friascop.appAP.mapper.MapperProducto;
import org.friascop.appAP.request.ProductoRequest;
import org.friascop.appAP.services.interfaces.InServ_Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class Control_Producto {

InServ_Producto servProducto;

    public Control_Producto (InServ_Producto servProducto) {
        this.servProducto = servProducto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long id) {

       Producto producto = servProducto.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con ID: " + id));

        return ResponseEntity.ok( producto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producto_dto>> getAllProductosZERO() {

        return ResponseEntity.ok(servProducto.findAllProductosGreaterThanZero());
    }

    @GetMapping("/search/1/{codigo_barras}")
    public ResponseEntity<Producto_dto> getProductoByCodigoBarrasZERO(@PathVariable String codigo_barras) {

        return ResponseEntity.ok(
                servProducto.findByCodigoBarrasGreaterThanZero(codigo_barras)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado, sin Stock")));

    }

    @PutMapping("/addtoupdate")
    public ResponseEntity<Producto_dto> actualizarProducto(
            @RequestBody Producto_dto dto) {
        return ResponseEntity.ok(servProducto.saveOrUbdate(dto).get());
    }

    @GetMapping("/search/2/{codigo_barras}")
    public ResponseEntity<List<Producto_dto>> getProductoByCodigoBarrasOrNombreZERO(@PathVariable String codigo_barras) {
        return ResponseEntity.ok(
                servProducto.findByCodigoBarrasOrNombreGreaterThanZero(codigo_barras));
    }

    @GetMapping("/search/1z/{codigo_barras}")
    public ResponseEntity<Producto_dto> getProductoByCodigoBarras(@PathVariable String codigo_barras) {

        return ResponseEntity.ok(
                servProducto.findByCodigoBarras(codigo_barras)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado, sin Stock")));

    }
}
