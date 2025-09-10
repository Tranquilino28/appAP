package org.friascop.appAP.auxdb.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long codigo;

    @Column(nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String rol;

    @Column(name = "hash_salt", nullable = false)
    private String hashsalt;

    @Column(name = "hash_password", nullable = false)
    private String hashpassword;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pers_id", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
}

