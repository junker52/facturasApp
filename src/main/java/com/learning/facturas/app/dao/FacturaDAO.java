package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ricard on 12/07/2018.
 */
@Repository
public interface FacturaDAO extends CrudRepository<Factura, Long> {

}
