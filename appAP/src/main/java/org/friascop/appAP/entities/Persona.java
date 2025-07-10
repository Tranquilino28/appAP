package org.friascop.appAP.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer identificacion;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id")
    private Maestra tipoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "tipo_sexo_id")
    private Maestra tipoSexo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_estado_id")
    private Maestra tipoEstado;

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

    public Maestra getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Maestra tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Maestra getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(Maestra tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public Maestra getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(Maestra tipoEstado) {
        this.tipoEstado = tipoEstado;
    }
}
