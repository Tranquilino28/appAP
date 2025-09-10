package org.friascop.appAP.services.impl;

import org.friascop.appAP.auxdb.DetalleVenta;
import org.friascop.appAP.services.interfaces.InServ_DetalleVenta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_DetalleVenta implements InServ_DetalleVenta {


    @Override
    public List<DetalleVenta> findAll() {
        return List.of();
    }

    @Override
    public Optional<DetalleVenta> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DetalleVenta save(DetalleVenta entity) {
        return null;
    }

    @Override
    public Optional<DetalleVenta> deleteById(Long id) {
        return Optional.empty();
    }
}
