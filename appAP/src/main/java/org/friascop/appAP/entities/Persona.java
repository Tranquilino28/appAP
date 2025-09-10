package org.friascop.appAP.entities;

import jakarta.persistence.*;
import lombok.Data;


//@Table(name = "persona")
@Data
public class Persona  {

  /*  @Id
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_identificacion_id")
    private Maestra tipoIdentificacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_sexo_id")
    private Maestra tipoSexo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_estado_id")
    private Maestra tipoEstado;
*/
}
