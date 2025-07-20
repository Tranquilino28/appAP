package org.friascop.appAP.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class Admin {

    @GetMapping
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("👑 Bienvenido al panel ADMIN");
    }





}
