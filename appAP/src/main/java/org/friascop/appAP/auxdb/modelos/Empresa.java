package org.friascop.appAP.auxdb.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nit;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;


    private String direccion;


    private String telefono;

    private String email;

    @Lob
    private byte[] logo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_categoria")
    private Maestra tipo_de_Categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_empresa")
    private Maestra tipo_de_empresa;

    public Empresa(Long id) {
        this.id = id;
    }

}

