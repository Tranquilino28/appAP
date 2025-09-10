package org.friascop.appAP.services.impl;

import org.friascop.appAP.auxdb.modelos.Maestra;
import org.friascop.appAP.dto.Maestra_dto;
import org.friascop.appAP.repositories.InRepositorio_Maestra;
import org.friascop.appAP.services.interfaces.InServ_Maestra;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Maestra implements InServ_Maestra {

    // Se IMPLEMENTA todas las funciones del servicio CRUD DE PERSONA

    final private InRepositorio_Maestra repoMaestra;

    public Impl_Serv_Maestra(InRepositorio_Maestra repoMaestra) {

        this.repoMaestra = repoMaestra;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Maestra_dto> listarDependientes(String nombre_corto_del_padre) {

        Maestra padre =
                repoMaestra
                .findByNombreCortoAndDependenciaIsNull(nombre_corto_del_padre.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Tipo padre no encontrado: " + nombre_corto_del_padre));

        List<Maestra> hijos =
                repoMaestra
                .findByDependenciaAndEstado(padre.getId(), 2);

        return hijos.stream()
                .map(h -> new Maestra_dto(h.getId(),  h.getNombreLargo(), h.getNombreCorto()))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Maestra> findAll() {

        return (List<Maestra>) repoMaestra.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Maestra> findById(Long id) {

        return repoMaestra.findById(id);
    }


    @Transactional
    @Override
    public Maestra save(Maestra maestra) {

        return repoMaestra.save(maestra);
    }

    @Transactional
    @Override
    public Optional<Maestra> deleteById(Long id) {

        Optional<Maestra> maestraOptional = repoMaestra.findById(id);

        if(maestraOptional.isPresent()){

            repoMaestra.deleteById(id);
            return maestraOptional;
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public List<Maestra> saveAll(List<Maestra> maestras) {

        return (List<Maestra>) repoMaestra.saveAll(maestras);
    }

}
