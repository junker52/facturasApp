package com.learning.facturas.app.controllers;

import com.learning.facturas.app.models.Cliente;
import com.learning.facturas.app.models.Factura;
import com.learning.facturas.app.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Ricard on 11/07/2018.
 */
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/form/{cliente_id}")
    public String crear(@PathVariable(value = "cliente_id") Long cliente_id, Model model, RedirectAttributes redirectAttributes) {
        Cliente cliente = this.clienteService.findOne(cliente_id);
        if (cliente == null) {
            redirectAttributes.addFlashAttribute("danger", "El cliente no existe en la base de datos.");
            return "redirect:/listar";
        }
        Factura factura = new Factura();
        factura.setCliente(cliente);
        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Crear Factura");
        return "factura/form";
    }
}
