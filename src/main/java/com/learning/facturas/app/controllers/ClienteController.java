package com.learning.facturas.app.controllers;

import com.learning.facturas.app.dao.ClienteDAO;
import com.learning.facturas.app.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;


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
        model.addAttribute("clientes",this.clienteDAO.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){
        model.put("titulo","Formulario de cliente");
        Cliente cliente = new Cliente();
        model.put("cliente",cliente);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("titulo","Formulario de cliente");
            return "form";
        }
        this.clienteDAO.save(cliente);
        return "redirect:listar";
    }
}
