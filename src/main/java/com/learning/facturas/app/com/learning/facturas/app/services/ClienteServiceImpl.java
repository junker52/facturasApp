package com.learning.facturas.app.com.learning.facturas.app.services;

import com.learning.facturas.app.dao.ClienteDAO;
import com.learning.facturas.app.models.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ricard on 05/07/2018.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteServiceImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
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
        return this.clienteDAO.findOne(id);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        this.clienteDAO.deleteOne(id);
    }
}
