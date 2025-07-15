package org.friascop.appAP.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "maestras")
@Data
public class Maestra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_largo", length = 50, nullable = false)
    private String nombreLargo;

    @Column(name = "nombre_corto", length = 20, nullable = false)
    private String nombreCorto;

    @Column(name = "dependencia_id")
    private Integer dependenciaId;

    @Column(nullable = false)
    private Integer estado;

}
