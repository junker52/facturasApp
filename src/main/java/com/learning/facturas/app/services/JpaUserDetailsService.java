package com.learning.facturas.app.services;

import com.learning.facturas.app.dao.UsuarioDAO;
import com.learning.facturas.app.models.Role;
import com.learning.facturas.app.models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricard on 15/07/2018.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UsuarioDAO usuarioDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public JpaUserDetailsService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioDAO.findUsuarioByUsername(s);

        if (ObjectUtils.isEmpty(usuario)) {
            this.log.error("Error al recuperar el usuario");
            throw new UsernameNotFoundException("Error retrieving user");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        usuario.getAuthorities().forEach((Role role) -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getAuthority());
            authorities.add(grantedAuthority);
        });
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
    }
}
