package org.friascop.appAP.controllers;


import org.friascop.appAP.entities.Maestra;
import org.friascop.appAP.entities.Persona;
import org.friascop.appAP.services.InServ_Maestra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos")
public class Controller_Maestra {

    final private InServ_Maestra servMaestra;

    public Controller_Maestra(InServ_Maestra servMaestra) {
        this.servMaestra = servMaestra;
    }

    @GetMapping
    public ResponseEntity<List<Maestra>> list(){

        return ResponseEntity.ok(servMaestra.findAll());
    }

    //muestra todos los detallesd de la persona que se quiere mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Maestra> details(@PathVariable Long id) {

        Optional<Maestra> personaOptional = servMaestra.findById(id);

        if (personaOptional.isPresent()) {

            return ResponseEntity.ok(personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    //crea la persona pasandole el objeto de la perosna que se creo en otra clase
    @PostMapping
    public ResponseEntity<Maestra> create(@RequestBody Maestra maestra) {

        Maestra maestraDb = servMaestra.save(maestra);

        return  ResponseEntity.status(HttpStatus.CREATED).body(maestraDb);
    }

    //Actualiza la persona buscadolo por su id y modificando con los cambios que se pasan
    @PutMapping("/{id}")
    public ResponseEntity<Maestra> ubdate(@RequestBody Maestra maestra, @PathVariable Long id) {

        Optional<Maestra> maestraOptional = servMaestra.findById(id);

        if (maestraOptional.isPresent()) {

            Maestra maestraDB = maestraOptional.orElseThrow();

            maestraDB.setNombreCorto(maestra.getNombreCorto());
            maestraDB.setNombreLargo(maestra.getNombreLargo());
            maestraDB.setDependenciaId(maestra.getDependenciaId());
            maestraDB.setEstado(maestra.getEstado());

            return ResponseEntity.status(HttpStatus.CREATED).body(servMaestra.save(maestra));
        }

        return ResponseEntity.notFound().build();
    }

    //Eliminala persona buscadola por el id
    @DeleteMapping("/{id}")
    public ResponseEntity<Maestra> delete(@PathVariable Long id) {

        Optional<Maestra> maestraOptional = servMaestra.findById(id);

        if (maestraOptional.isPresent()) {

            Maestra deleteMaestra = maestraOptional.orElseThrow();

            return ResponseEntity.status(HttpStatus.OK).body(deleteMaestra);
        }
        return ResponseEntity.notFound().build();

    }




}
