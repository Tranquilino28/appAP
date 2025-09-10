package org.friascop.appAP.controllers;

import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.services.interfaces.InServ_Empresa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class Control_Empresa {

    final private InServ_Empresa servEmpresa;

    public Control_Empresa(InServ_Empresa servEmpresa) {

        this.servEmpresa = servEmpresa;
    }


    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody Empresa empresa) {
        return new ResponseEntity<>(servEmpresa.save(empresa), HttpStatus.CREATED);
    }




}
