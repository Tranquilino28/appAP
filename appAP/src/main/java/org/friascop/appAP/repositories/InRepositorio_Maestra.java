package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.modelos.Maestra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InRepositorio_Maestra extends CrudRepository<Maestra,Long> {

    // 1. Buscar el padre por nombre corto
    Optional<Maestra> findByNombreCortoAndDependenciaIsNull(String nombreCorto);

    // 2. Buscar hijos activos por id del padre
    List<Maestra> findByDependenciaAndEstado(Long dependencia, Integer estado);

    // 3. Obtener todos los padres (sin dependencia)
    List<Maestra> findByDependenciaIsNull();

    // 4. Obtener todos los hijos de un padre específico
    List<Maestra> findByDependencia(Long idPadre);

}
