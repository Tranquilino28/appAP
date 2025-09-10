package org.friascop.appAP.auxdb;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.modelos.Producto;

@Entity
@Table(name = "carritocompras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clie_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

}


