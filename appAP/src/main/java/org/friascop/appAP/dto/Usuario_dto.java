/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.friascop.appAP.dto;

import jakarta.persistence.*;
import lombok.Data;

/**
 *
 * @author usuario
 */
@Data
public class Usuario_dto {


    private Long id;

    private Long usua_codigo;
    private String usua_usuario;
    private String usua_rol;

    private Persona_dto persona;

    //private Persona persona;
    public Usuario_dto(Long id, Long usua_codigo, String usua_usuario, String usua_rol, Persona_dto persona) {
        this.id = id;
        this.usua_codigo = usua_codigo;
        this.usua_usuario = usua_usuario;
        this.usua_rol = usua_rol;
        this.persona = persona;
    }

}
