package org.friascop.appAP.services;

import org.friascop.appAP.entities.Maestra;
import org.friascop.appAP.entities.Persona;
import org.friascop.appAP.repositories.InRepositorio_Maestra;
import org.friascop.appAP.repositories.InRepositorio_Persona;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Maestra implements InServ_Maestra {

    // Se IMPLEMENTA todas las funciones del servicio CRUD DE PERSONA

    final private InRepositorio_Maestra RepoMaestra;

    public Impl_Serv_Maestra(InRepositorio_Maestra repoMaestra) {
        this.RepoMaestra = repoMaestra;

    }

    @Transactional(readOnly = true)
    @Override
    public List<Maestra> findAll() {

        return (List<Maestra>) RepoMaestra.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Maestra> findById(Long id) {

        return RepoMaestra.findById(id);
    }


    @Transactional
    @Override
    public Maestra save(Maestra maestra) {

        return RepoMaestra.save(maestra);
    }

    @Transactional
    @Override
    public Optional<Maestra> deleteById(Long id) {

        Optional<Maestra> maestraOptional = RepoMaestra.findById(id);

        if(maestraOptional.isPresent()){

            RepoMaestra.deleteById(id);
            return maestraOptional;
        }

        return Optional.empty();
    }


}
