/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.friascop.appAP.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario_dto {
    private Long id;
    private Long codigo;
    private String nombreUsuario;
    private String rol;
    private Long personaId;   // relación con Persona
    private Long empresaId;   // relación con Empresa
}