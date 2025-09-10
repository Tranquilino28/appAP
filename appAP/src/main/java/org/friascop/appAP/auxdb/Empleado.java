package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Maestra;
import org.friascop.appAP.auxdb.modelos.Persona;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "pers_id")
    private Persona persona;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_especialidad")
    private Maestra tipo_de_especialidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_rol")
    private Maestra tipo_de_Rol;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}

