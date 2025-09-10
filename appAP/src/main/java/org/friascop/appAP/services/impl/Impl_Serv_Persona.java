package org.friascop.appAP.services.impl;

import org.friascop.appAP.services.interfaces.InServ_Persona;
import org.springframework.transaction.annotation.Transactional;
import org.friascop.appAP.auxdb.modelos.Persona;
import org.friascop.appAP.repositories.InRepositorio_Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Persona implements InServ_Persona {

    // Se IMPLEMENTA todas las funciones del servicio CRUD DE PERSONA

    final private InRepositorio_Persona RepoProducto;

    public Impl_Serv_Persona(InRepositorio_Persona repoProducto) {

        RepoProducto = repoProducto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Persona> findAll() {

        return (List<Persona>) RepoProducto.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Persona> findById(Long id) {

        return RepoProducto.findById(id);
    }


    @Transactional
    @Override
    public Persona save(Persona Persona) {

        return RepoProducto.save(Persona);
    }

    @Transactional
    @Override
    public Optional<Persona> deleteById(Long id) {

        Optional<Persona> personaOptional = RepoProducto.findById(id);

        if(personaOptional.isPresent()){

            RepoProducto.deleteById(id);
            return personaOptional;
        }

        return Optional.empty();
    }


}
