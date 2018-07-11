package com.learning.facturas.app.services;

import com.learning.facturas.app.dao.ClienteDAO;
import com.learning.facturas.app.dao.ProductoDAO;
import com.learning.facturas.app.models.Cliente;
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

    private final static int PAGESIZE = 5;

    public ClienteServiceImpl(ClienteDAO clienteDAO, ProductoDAO productoDAO) {
        this.clienteDAO = clienteDAO;
        this.productoDAO = productoDAO;
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
    public Page<Cliente> getPage(int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber - 1, PAGESIZE);
        Page<Cliente> clientePage = this.clienteDAO.findAll(request);
        clientePage.getNumber();
        return clientePage;
    }

    @Override
    public List<Producto> findByNombre(String nombre) {
        return this.productoDAO.findByNombre(nombre);
    }


}
