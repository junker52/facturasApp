package com.learning.facturas.app.dao;

import com.learning.facturas.app.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ricard on 15/07/2018.
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findUsuarioByUsername(String username);
}
