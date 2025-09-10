package org.friascop.appAP.repositories;

import org.friascop.appAP.auxdb.modelos.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InRepositorio_Producto extends CrudRepository<Producto,Long> {


    // Spring Data JPA generará la consulta automáticamente
    Optional<Producto> findByCodigo(String codigo);

    // busca por codigo de barras y nombre


    List<Producto> findByCodigoContainingIgnoreCaseOrNombreContainingIgnoreCase(String codigo, String nombre);

    List<Producto> findAllByCodigoIn(List<String> codigos);

   /*  Útil para consultas más complejas o cuando no se sigue el naming convention
    @Query("SELECT p FROM Producto p WHERE p.codigoBarras = :codigo")
    Optional<Producto> buscarPorCodigoDeBarras(@Param("codigo") String codigo);

    */

    @Query("SELECT p " +
            "FROM Producto p " +
            "WHERE (" +
            "LOWER(p.codigo) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))" +
            ") " +
            "AND p.stockDisponible > 0")
    List<Producto> findByCodigoOrNombreAndStockGreaterThanZero(@Param("texto") String texto);



    @Query("SELECT p FROM Producto p WHERE p.codigo = :codigo AND p.stockDisponible > 0")
    Optional<Producto> findByCodigoAndStockGreaterThanZero(@Param("codigo") String codigo);


}
