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
    private String hash_salt;
    private String hash_password;

    //private Persona persona;
    public Usuario(Long usua_codigo, String usua_usuario, String hash_salt, String hash_password) {

        this.usua_codigo = usua_codigo;
        this.usua_usuario = usua_usuario;
        this.hash_salt = hash_salt;
        this.hash_password = hash_password;
    }

    public Usuario() {

    }


    public Long getUsua_codigo() {
        return usua_codigo;
    }

    public String getUsua_usuario() {
        return usua_usuario;
    }

    public String getHash_salt() {
        return hash_salt;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsua_codigo(Long usua_codigo) {
        this.usua_codigo = usua_codigo;
    }

    public void setUsua_usuario(String usua_usuario) {
        this.usua_usuario = usua_usuario;
    }

    public void setHash_salt(String hash_salt) {
        this.hash_salt = hash_salt;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }
}
