package org.friascop.appAP.auxdb.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "maestra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maes_nombrelargo", nullable = false)
    private String nombreLargo;

    @Column(name = "maes_nombrecorto", nullable = false)
    private String nombreCorto;

    @Column(name = "maes_dependencia")
    private Integer dependencia;

    @Column(name = "maes_estado", nullable = false)
    private Integer estado;

    // este constructor extra lo usamos para mapear DTOs sin tener que traer toda la entidad
    public Maestra(Long id) {
        this.id = id;
    }

}
