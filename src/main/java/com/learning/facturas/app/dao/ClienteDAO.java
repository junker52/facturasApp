package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ricard on 04/07/2018.
 */
public interface ClienteDAO extends PagingAndSortingRepository<Cliente, Long> {

    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    void delete(Cliente cliente);
}
