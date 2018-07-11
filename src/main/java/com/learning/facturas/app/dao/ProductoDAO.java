package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ricard on 11/07/2018.
 */
@Repository
public interface ProductoDAO extends CrudRepository<Producto, Long> {

    @Query("select p from Producto p where p.nombre like %?1%")
    List<Producto> findByNombre(String nombre);

    //List<Producto> findByNombreLikeIgnoreCase(String nombre);
}
