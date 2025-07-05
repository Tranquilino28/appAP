package org.friascop.appAP.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_PERSONAS")
public class Persona {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tpers_id;

    private Long tpers_dni;

    private String tpers_nombres;


    private String tpers_apellidos;

    public Persona(){}

    public long getTpers_id() {
        return tpers_id;
    }

    public void setTpers_id(long tpers_id) {
        this.tpers_id = tpers_id;
    }

    public Long getTpers_dni() {
        return tpers_dni;
    }

    public void setTpers_dni(Long tpers_dni) {
        this.tpers_dni = tpers_dni;
    }

    public String getTpers_apellidos() {
        return tpers_apellidos;
    }

    public void setTpers_apellidos(String tpers_apellidos) {
        this.tpers_apellidos = tpers_apellidos;
    }

    public String getTpers_nombres() {
        return tpers_nombres;
    }

    public void setTpers_nombres(String tpers_nombres) {
        this.tpers_nombres = tpers_nombres;
    }
}
