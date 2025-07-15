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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(String tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }
}
