package org.friascop.appAP.controllers;



import org.friascop.appAP.entities.Persona;
import org.friascop.appAP.services.InServ_Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/home")
public class Controller_Home {

    /**
     * Se crea la variable inmutable del servicioPersona
     * se pasan todos los atributos en esta clase del servicio persona
     *
     *
     */



    final private InServ_Persona servPersona;

    public Controller_Home(InServ_Persona servPersona) {

        this.servPersona = servPersona;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> list(){

        return ResponseEntity.ok(servPersona.findAll());
    }

    //muestra todos los detallesd de la persona que se quiere mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Persona> details(@PathVariable Long id) {

        Optional<Persona> personaOptional = servPersona.findById(id);

        if (personaOptional.isPresent()) {

            return ResponseEntity.ok(personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    //crea la persona pasandole el objeto de la perosna que se creo en otra clase
    @PostMapping
    public ResponseEntity<Persona> create(@RequestBody Persona persona) {

        Persona personaDb = servPersona.save(persona);

        return  ResponseEntity.status(HttpStatus.CREATED).body(personaDb);
    }

    //Actualiza la persona buscadolo por su id y modificando con los cambios que se pasan
    @PutMapping("/{id}")
    public ResponseEntity<Persona> ubdate(@RequestBody Persona persona, @PathVariable Long id) {

      Optional<Persona> personaOptional = servPersona.findById(id);

      if (personaOptional.isPresent()) {

          Persona personaDb = personaOptional.orElseThrow();

          personaDb.setIdentificacion(persona.getIdentificacion());
          personaDb.setNombre(persona.getNombre());
          personaDb.setApellido(persona.getApellido());

          return ResponseEntity.status(HttpStatus.CREATED).body(servPersona.save(personaDb));
      }

      return ResponseEntity.notFound().build();
    }

    //Eliminala persona buscadola por el id
    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> delete(@PathVariable Long id) {

        Optional<Persona> personaOptional = servPersona.findById(id);

        if (personaOptional.isPresent()) {

            Persona deletePersona = personaOptional.orElseThrow();

            return ResponseEntity.status(HttpStatus.OK).body(deletePersona);
        }
        return ResponseEntity.notFound().build();

    }
}
