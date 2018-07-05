package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ricard on 04/07/2018.
 */
@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0 ) {
            entityManager.merge(cliente);
        } else {
            this.entityManager.persist(cliente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        Cliente cliente = this.findOne(id);
        if (cliente != null){
            this.entityManager.remove(cliente);
        }
    }
}
