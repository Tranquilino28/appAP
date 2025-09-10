package org.friascop.appAP.services.impl;


import org.friascop.appAP.dto.UsuarioPerfil_dto;
import org.friascop.appAP.dto.Usuario_dto;
import org.friascop.appAP.auxdb.modelos.Persona;
import org.friascop.appAP.auxdb.modelos.Usuario;
import org.friascop.appAP.mapper.MapperUsuario;
import org.friascop.appAP.repositories.InRepositorio_Persona;
import org.friascop.appAP.repositories.InRepositorio_Usuario;
import org.friascop.appAP.services.interfaces.InServ_Usuario;
import org.friascop.appAP.util.PassSecure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Usuario implements InServ_Usuario {



    // Se IMPLEMENTA todas las funciones del servicio CRUD DE usuario

    final private InRepositorio_Usuario repoUsuario;
    final private InRepositorio_Persona repoPersona;



    public Impl_Serv_Usuario(InRepositorio_Usuario repoUsuario, InRepositorio_Persona repoPersona) {

        this.repoUsuario = repoUsuario;
        this.repoPersona = repoPersona;
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

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario_dto> findById_dto(Long id) {
         Optional<Usuario> u = findById(id);

        return Optional.of(MapperUsuario.toDto(u.get()));
    }

    @Override
    public Optional<Usuario> validarUsuario(String usuario) {
        System.out.println("se busco el usuario");
        return repoUsuario.findByNombreUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public boolean usuarioExists(String usuario, String password) {
       Optional<Usuario> user = repoUsuario.findByNombreUsuario(usuario);
              boolean exists = false; 
               
                if (user.isPresent()) {
                    System.out.println("el suuario se va a validadr");
                    try {
                        exists =   PassSecure.verifyPassword(
                                password
                                , user.get().getHashsalt()
                                , user.get().getHashpassword()
                                );
                    } catch (InvalidKeySpecException e) {
                        throw new RuntimeException(e);
                    }


                }

        return exists;
        
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return Optional.empty();
    }


    @Transactional
    @Override
    public Usuario save(Usuario usuario)  {
        try {

            usuario.setHashsalt(PassSecure.generateSalt());

            usuario.setHashpassword(PassSecure.hashPassword(usuario.getHashpassword(), usuario.getHashsalt()));

        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }


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

    @Transactional
    @Override
    public Usuario crearUsuario(Usuario usuario, Persona p) {
        // 1. Buscar la persona por su ID
        Persona persona = repoPersona.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // 3. Guardar en la base de datos
        return repoUsuario.save(usuario);
    }

    @Transactional
    @Override
   public  Optional<UsuarioPerfil_dto> usuarioPerfil(String nombreUsuario) {
        Optional<Usuario> userOpt = repoUsuario.findByNombreUsuario(nombreUsuario);

        if (userOpt.isEmpty()) {
            return Optional.empty(); // No se encontró el usuario
        }

        Usuario user = userOpt.get();
        Persona p = user.getPersona();

        UsuarioPerfil_dto dto = new UsuarioPerfil_dto();
        dto.setNombre(p.getNombre());  // Asignamos nombre desde Persona
        dto.setRol(user.getRol());     // Asignamos rol desde Usuario

        return Optional.of(dto);
    }
}
