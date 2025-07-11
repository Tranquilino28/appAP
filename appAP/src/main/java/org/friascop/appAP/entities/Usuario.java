/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.friascop.appAP.entities;

import jakarta.persistence.*;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usua_codigo;
    private String usua_usuario;
    private String usua_rol;
    private String hash_salt;
    private String hash_password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pers_id", nullable = false)
    private Persona persona;

    //private Persona persona;
    public Usuario(Long usua_codigo, String usua_usuario,String usua_rol, String hash_salt, String hash_password, Persona persona) {

        this.usua_codigo = usua_codigo;
        this.usua_usuario = usua_usuario;
        this.usua_rol = usua_rol;
        this.hash_salt = hash_salt;
        this.hash_password = hash_password;
        this.persona = persona;
    }

    public Usuario() {

    }



    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsua_codigo() {
        return usua_codigo;
    }

    public void setUsua_codigo(Long usua_codigo) {
        this.usua_codigo = usua_codigo;
    }

    public String getUsua_usuario() {
        return usua_usuario;
    }

    public void setUsua_usuario(String usua_usuario) {
        this.usua_usuario = usua_usuario;
    }

    public String getUsua_rol() {
        return usua_rol;
    }

    public void setUsua_rol(String usua_rol) {
        this.usua_rol = usua_rol;
    }

    public String getHash_salt() {
        return hash_salt;
    }

    public void setHash_salt(String hash_salt) {
        this.hash_salt = hash_salt;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }
}
