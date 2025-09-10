package org.friascop.appAP.controllers;

import org.friascop.appAP.dto.Venta_dto;
import org.friascop.appAP.request.VentaRequest;
import org.friascop.appAP.services.interfaces.InServ_Venta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class Control_Venta {

    final private InServ_Venta servVenta;

    public Control_Venta(InServ_Venta servVenta) {
        this.servVenta = servVenta;
    }


    @PostMapping("/save")
    public ResponseEntity<Venta_dto> guardarVenta(@RequestBody VentaRequest ventaRequest) {

        return ResponseEntity.ok(servVenta.createVenta(ventaRequest));
    }





}
