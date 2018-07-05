package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Cliente;

import java.util.List;

/**
 * Created by Ricard on 04/07/2018.
 */
public interface ClienteDAO {

    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);
}
