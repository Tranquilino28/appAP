package org.friascop.appAP.services.impl;

import org.friascop.appAP.auxdb.modelos.Producto;
import org.friascop.appAP.dto.Producto_dto;
import org.friascop.appAP.mapper.MapperProducto;
import org.friascop.appAP.repositories.InRepositorio_Producto;
import org.friascop.appAP.services.interfaces.InServ_Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Producto implements InServ_Producto {

    // Se IMPLEMENTA todas las funciones del servicio CRUD DE PERSONA

    final private InRepositorio_Producto repoProducto;

    public Impl_Serv_Producto(InRepositorio_Producto repoProducto) {

        this.repoProducto = repoProducto;
    }

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll() {

        return (List<Producto>) repoProducto.findAll();
    }

    /**
     * busca un producto por su id
     * @param id del producto
     * @return el producto si tiene almenos una medida en stock
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> findById(Long id) {

        return repoProducto.findById(id);
    }

    /**
     * busca un producto por su codigo de barras
     * @param codigo de barras del producto
     * @return el producto si tiene una memedia en stock
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Producto_dto> findByCodigoBarrasGreaterThanZero(String codigo) {

        Optional<Producto> p = repoProducto.findByCodigoAndStockGreaterThanZero(codigo);

        if (p.isEmpty()) {
            return Optional.empty();

        }


        return Optional.of(MapperProducto.toDto(p.get()));
    }


    /**
     * busca todos los productos que estan en stock
     * @return los productos que estan en stock
     */
    @Transactional
    @Override
    public List<Producto_dto> findAllProductosGreaterThanZero() {

        List<Producto> productos = findAll();

        List<Producto_dto> p = new ArrayList<>();


        for (Producto producto : productos) {

            p.add(new Producto_dto(
                    producto.getId()
                    , producto.getNombre()
                    , producto.getDescripcion()
                    , producto.getCodigo()
                    , producto.getPrecio()
                    , producto.getCategoria()
                    , producto.getMedida()
                    , producto.getStockDisponible()
                    , producto.getEmpresa().getId())
            );

        }

        return p;
    }

    /**
     * busca los productos que coinciden en nombre y en el codigo de barras
     * @param texto ya sea del codigo de barras o el nombre
     * @return los productos coincidentes
     */
    @Transactional
    @Override
    public List<Producto_dto> findByCodigoBarrasOrNombreGreaterThanZero(String texto) {

            List<Producto> productos = repoProducto
                    .findByCodigoOrNombreAndStockGreaterThanZero(texto);

        List<Producto_dto> p = new ArrayList<>();

        for (Producto producto : productos) {

            p.add(new Producto_dto(
                    producto.getId()
                    , producto.getNombre()
                    , producto.getDescripcion()
                    , producto.getCodigo()
                    , producto.getPrecio()
                    , producto.getCategoria()
                    , producto.getMedida()
                    , producto.getStockDisponible()
                    ,producto.getEmpresa().getId())
            );
        }
            return p;

    }

   @Override
    public Optional<Producto_dto> findByCodigoBarras(String codigo) {

        Optional<Producto> p = repoProducto.findByCodigo(codigo);

        if (p.isEmpty()) {
            return Optional.empty();

        }
        return Optional.of(MapperProducto.toDto(p.get()));
    }

    @Override
    public Optional<Producto_dto> saveOrUbdate(Producto_dto dto) {

              Optional<Producto> op =  repoProducto.findByCodigo(dto.getCodigoBarra());


              if (!op.isEmpty()) {
                  Producto p = op.get();

                  p.setNombre(dto.getNombre());
                  p.setDescripcion(dto.getDescripcion());
                  p.setStockDisponible(dto.getStockDisponible());
                  p.setPrecio(dto.getPrecio());
                  p.setCategoria(dto.getCategoria());
                  p.setMedida(dto.getMedida());

                 return Optional.of(MapperProducto.toDto(save(p)));

              }else{




                  return Optional.of(
                          MapperProducto.toDto(
                                  save(
                                          MapperProducto.toEntity(dto)
                                  )
                          )
                  );
              }


    }

    /**
     * guarda un prodcuto nuevo
     * @param producto
     * @return el mismo producto guardado
     */
    @Transactional
    @Override
    public Producto save(Producto producto) {

        return repoProducto.save(producto);
    }

    /**
     * borra un poroducto por su id
     * @param id
     * @return un ok response 200
     */
    @Transactional
    @Override
    public Optional<Producto> deleteById(Long id) {

        Optional<Producto> productoOptional = repoProducto.findById(id);

        if(productoOptional.isPresent()){

            repoProducto.deleteById(id);
            return productoOptional;
        }

        return Optional.empty();
    }


}
