package org.friascop.appAP.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Maestra_dto {

    private Long id;
    private String nombreLargo;
    private String nombreCorto;

}
