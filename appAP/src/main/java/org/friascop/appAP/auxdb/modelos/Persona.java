package org.friascop.appAP.auxdb.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String identificacion;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;


    private String direccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_identificacion")
    private Maestra tipo_de_identificacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_sexo")
    private Maestra tipo_de_sexo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_estado")
    private Maestra estado;

    public Persona(Long id) {
        this.id = id;
    }
}

