package com.learning.facturas.app.controllers;

import com.learning.facturas.app.dao.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Ricard on 04/07/2018.
 */
@Controller
public class ClienteController {

    @Autowired
    private ClienteDAO clienteDAO;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo","Listado de Clientes");
        model.addAttribute("clientes",clienteDAO.findAll());
        return "listar";
    }
}
