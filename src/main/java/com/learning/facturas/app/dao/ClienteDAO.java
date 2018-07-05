package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ricard on 04/07/2018.
 */
public interface ClienteDAO extends CrudRepository<Cliente, Long>{

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    void delete(Cliente cliente);
}
