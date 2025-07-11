package org.friascop.appAP.dto;

import jakarta.persistence.*;


public class Persona_dto {


    private Long id;


    private Integer identificacion;


    private String nombre;


    private String apellido;


    private String direccion;


    private String tipoIdentificacion;


    private String tipoSexo;


    private String tipoEstado;

    public Persona_dto(Long id
            , Integer identificacion
            , String nombre
            , String apellido
            , String direccion
            , String tipoIdentificacion
            , String tipoSexo
            , String tipoEstado

    ) {

        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.tipoSexo = tipoSexo;
        this.tipoEstado = tipoEstado;
    }

}
