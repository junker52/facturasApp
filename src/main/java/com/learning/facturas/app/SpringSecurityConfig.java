package com.learning.facturas.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Ricard on 13/07/2018.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User.UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        //Creamos usuarios en memoria como primer paso
        builder.inMemoryAuthentication()
                .withUser(users.username("admin").password("123").roles("ADMIN", "USER"))
                .withUser(users.username("ricard").password("123").roles("USER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
                .antMatchers("/ver/**").hasAnyRole("USER")
                .antMatchers("/uploads/**").hasAnyRole("USER")
                .antMatchers("/form/**").hasAnyRole("ADMIN")
                .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
                .antMatchers("/factura/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated();

    }
}
