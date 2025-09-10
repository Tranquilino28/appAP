package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;

import java.time.LocalDate;

@Entity
@Table(name = "descuentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private Integer porcentaje;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}
