package org.friascop.appAP.controllers;


import org.friascop.appAP.auxdb.modelos.Maestra;
import org.friascop.appAP.dto.Maestra_dto;
import org.friascop.appAP.services.interfaces.InServ_Maestra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/master")
public class MasterTable {

    final private InServ_Maestra servMaestra;

    public MasterTable(InServ_Maestra servMaestra) {
        this.servMaestra = servMaestra;
    }

    @GetMapping
    public ResponseEntity<List<Maestra>> list(){

        return ResponseEntity.ok(servMaestra.findAll());
    }

    /**
     * Muestra los detalles de un registro en especifico de la tabla maestra
     * @param id del registro que se quiere mostrar
     * @return registro encontrado si existe
     */
    //muestra todos los detallesd de la persona que se quiere mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Maestra> details(@PathVariable Long id) {

        Optional<Maestra> maestraOptional = servMaestra.findById(id);

        if (maestraOptional.isPresent()) {

            return ResponseEntity.ok(maestraOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    /**
     *
     * @param maestra , el registro de maestra a guardar en la base de datos
     * @return el registro creado
     */
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
            maestraDB.setDependencia(maestra.getDependencia());
            maestraDB.setEstado(maestra.getEstado());

            return ResponseEntity.status(HttpStatus.CREATED).body(servMaestra.save(maestra));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create/bulk")
    public ResponseEntity<List<Maestra>> createBulk(@RequestBody List<Maestra> maestras) {
        List<Maestra> maestrasDb = servMaestra.saveAll(maestras);
        return ResponseEntity.status(HttpStatus.CREATED).body(maestrasDb);
    }


    /**
     *
     * @param tipo , recibe el nombre corto del tipo de dato
     * @return una lista que le pertenecen a ese tipo de dato
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Maestra_dto>> getValores(@PathVariable String tipo) {
        System.out.println(tipo);
        return ResponseEntity.ok(servMaestra.listarDependientes(tipo));
    }


    /**
     * metodos que buscan los tipos de resgisto,
     * @return
     */
    @GetMapping("/tipo/estado")
    public ResponseEntity<List<Maestra_dto>> getTiposEstado() {

       List<Maestra_dto> tipos = servMaestra.listarDependientes("ties");

        tipos.forEach(tipo -> {
            System.out.println("Nombre Largo: " + tipo.getNombreLargo());
            System.out.println("Nombre Corto: " + tipo.getNombreCorto());
        });

        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/tipo/identificacion")
    public ResponseEntity<List<Maestra_dto>> getTiposIdentificacion() {
        return ResponseEntity.ok(servMaestra.listarDependientes("tiid"));
    }

    @GetMapping("/tipo/sexo")
    public ResponseEntity<List<Maestra_dto>> getTiposSexo() {
        return ResponseEntity.ok(servMaestra.listarDependientes("tisex"));
    }

    @GetMapping("/tipo/bebida")
    public ResponseEntity<List<Maestra_dto>> getTiposBebida() {
        return ResponseEntity.ok(servMaestra.listarDependientes("tibeb"));
    }

}