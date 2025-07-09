package org.friascop.appAP.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONA")
public class Persona {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pers_id;

    private Long pers_dni;

    private String pers_nombres;

    private String pers_apellidos;

    public Persona(Long pers_dni, String pers_nombres, String pers_apellidos) {
        this.pers_dni = pers_dni;
        this.pers_nombres = pers_nombres;
        this.pers_apellidos = pers_apellidos;

    }

    public Persona() {

    }

    public Long getPers_dni() {
        return pers_dni;
    }

    public String getPers_nombres() {
        return pers_nombres;
    }

    public String getPers_apellidos() {
        return pers_apellidos;
    }
}
