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
@Table(name = "TABLA_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigo;
    private String usuario;
    private String hash_salt;
    private String hash_password;

    public Usuario(Long codigo, String usuario, String hash_salt, String hash_password) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.hash_salt = hash_salt;
        this.hash_password = hash_password;
    }

    public Usuario(String usuario, String hash_salt, String hash_password) {
        this.usuario = usuario;
        this.hash_salt = hash_salt;
        this.hash_password = hash_password;
    }

    public Usuario() {

    }

    public Long getCodigo() {
        return codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getHash_salt() {
        return hash_salt;
    }

    public String getHash_password() {
        return hash_password;
    }

   
}
