package org.friascop.appAP.services;

import org.friascop.appAP.entities.Maestra;
import org.friascop.appAP.entities.Usuario;
import org.friascop.appAP.repositories.InRepositorio_Maestra;
import org.friascop.appAP.repositories.InRepositorio_Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Usuario implements InServ_Usuario {

    // Se IMPLEMENTA todas las funciones del servicio CRUD DE PERSONA

    final private InRepositorio_Usuario repoUsuario;

    public Impl_Serv_Usuario( InRepositorio_Usuario repoUsuario) {

        this.repoUsuario = repoUsuario;

    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {

        return (List<Usuario>) repoUsuario.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findById(Long id) {

        return repoUsuario.findById(id);
    }


    @Transactional
    @Override
    public Usuario save(Usuario usuario) {

        return repoUsuario.save(usuario);
    }

    @Transactional
    @Override
    public Optional<Usuario> deleteById(Long id) {

        Optional<Usuario> usuarioOptional = repoUsuario.findById(id);

        if(usuarioOptional.isPresent()){

            repoUsuario.deleteById(id);
            return usuarioOptional;
        }

        return Optional.empty();
    }


}
