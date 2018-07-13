package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ricard on 12/07/2018.
 */
@Repository
public interface FacturaDAO extends CrudRepository<Factura, Long> {

    @Query("select f from Factura f join fetch f.cliente c join fetch f.items i join fetch i.producto where f.id = ?1")
    Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);

}
