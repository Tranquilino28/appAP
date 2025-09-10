package org.friascop.appAP.genericServices;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    // Lista todos los registros de cualquier tipo de entidad
    List<T> findAll();

    // Búsqueda por ID, usando Optional para manejar respuestas vacías
    Optional<T> findById(Long id);

    // Guardar una entidad genérica
    T save(T entity);


    // Eliminar por ID
    Optional<T> deleteById(Long id);
}
