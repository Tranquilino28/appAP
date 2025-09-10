package org.friascop.appAP.auxdb.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friascop.appAP.auxdb.Cliente;
import org.friascop.appAP.auxdb.DetalleVenta;
import org.friascop.appAP.auxdb.Pedido;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @ColumnDefault("random_uuid()") // ✅ Esto asigna un UUID en el momento de la creación
    private UUID codigoVenta;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;

    @ManyToOne(optional = false) // cada venta pertenece a un cliente
    @JoinColumn(name = "clie_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "pedi_id", nullable = true)
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_metodopago")
    private Maestra metodo_de_pago;


    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;

    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetalleVenta> detalles = new ArrayList<>();

}

