package org.friascop.appAP.services.impl;


import org.friascop.appAP.auxdb.CarritoCompras;
import org.friascop.appAP.dto.CarritoCompras_dto;
import org.friascop.appAP.services.interfaces.InServ_CarritoCompras;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_CarritoCompras implements InServ_CarritoCompras {


    @Override
    public Optional<CarritoCompras_dto> findById_dto(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CarritoCompras> findAll() {
        return List.of();
    }

    @Override
    public Optional<CarritoCompras> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CarritoCompras save(CarritoCompras entity) {
        return null;
    }

    @Override
    public Optional<CarritoCompras> deleteById(Long id) {
        return Optional.empty();
    }
}
