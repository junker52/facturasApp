package com.learning.facturas.app.com.learning.facturas.app.services;

import com.learning.facturas.app.models.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Ricard on 05/07/2018.
 */
public interface ClienteService {

    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);

    void deleteOne(Long id);

    Page<Cliente> getPage(int pageNumber);
}
