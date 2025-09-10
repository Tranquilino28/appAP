package org.friascop.appAP.auxdb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.auxdb.modelos.Producto;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "clie_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}

