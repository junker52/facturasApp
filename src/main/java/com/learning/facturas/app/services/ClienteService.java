package com.learning.facturas.app.services;

import com.learning.facturas.app.models.Cliente;
import com.learning.facturas.app.models.Factura;
import com.learning.facturas.app.models.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Ricard on 05/07/2018.
 */
public interface ClienteService {

    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);

    Cliente deleteOne(Long id);

    Page<Cliente> getPage(int pageNumber);

    List<Producto> findByNombre(String nombre);

    void saveFactura(Factura factura);

    Producto findProductoById(Long id);

    Factura findFacturaById(Long id);

    void deleteFactura(Long id);

    Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
