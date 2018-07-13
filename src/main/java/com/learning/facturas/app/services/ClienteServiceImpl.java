package com.learning.facturas.app.services;

import com.learning.facturas.app.dao.ClienteDAO;
import com.learning.facturas.app.dao.FacturaDAO;
import com.learning.facturas.app.dao.ProductoDAO;
import com.learning.facturas.app.models.Cliente;
import com.learning.facturas.app.models.Factura;
import com.learning.facturas.app.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ricard on 05/07/2018.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;
    private FacturaDAO facturaDAO;

    private final static int PAGESIZE = 5;

    public ClienteServiceImpl(ClienteDAO clienteDAO, ProductoDAO productoDAO, FacturaDAO facturaDAO) {
        this.clienteDAO = clienteDAO;
        this.productoDAO = productoDAO;
        this.facturaDAO = facturaDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return this.clienteDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        this.clienteDAO.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return this.clienteDAO.findById(id).orElse(new Cliente());
    }

    @Override
    @Transactional
    public Cliente deleteOne(Long id) {
        Cliente cliente = this.findOne(id);
        this.clienteDAO.delete(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Page<Cliente> getPage(int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber - 1, PAGESIZE);
        Page<Cliente> clientePage = this.clienteDAO.findAll(request);
        clientePage.getNumber();
        return clientePage;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String nombre) {
        return this.productoDAO.findByNombre(nombre);
    }

    @Override
    @Transactional
    public void saveFactura(Factura factura) {
        this.facturaDAO.save(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Long id) {
        return this.productoDAO.findById(id).orElse(new Producto());
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return this.facturaDAO.findById(id).orElse(new Factura());
    }

    @Override
    @Transactional
    public void deleteFactura(Long id) {
        this.facturaDAO.deleteById(id);
    }

    @Override
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id) {
        return this.facturaDAO.fetchByIdWithClienteWithItemFacturaWithProducto(id);
    }


}
