package com.learning.facturas.app.controllers;

import com.learning.facturas.app.models.Cliente;
import com.learning.facturas.app.models.Factura;
import com.learning.facturas.app.models.ItemFactura;
import com.learning.facturas.app.models.Producto;
import com.learning.facturas.app.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ricard on 11/07/2018.
 */
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private ClienteService clienteService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping(value = "/cargar-productos/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Producto> cargarProductos(@PathVariable("nombre") String nombre) {
        return this.clienteService.findByNombre(nombre);
    }

    @PostMapping("/form")
    public String guardar(@Valid Factura factura, BindingResult bindingResult, Model model,
                          @RequestParam(value = "item_id[]", required = false) Long[] itemId,
                          @RequestParam(value = "cantidad[]", required = false) Integer[] cantidad,
                          RedirectAttributes redirectAttributes,
                          SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Crear Factura");
            return "factura/form";
        }
        if (itemId == null || itemId.length == 0) {
            model.addAttribute("titulo", "Crear Factura");
            model.addAttribute("danger", "La factura debe tener una línea almenos.");
            return "factura/form";
        }
        for (int i = 0; i < itemId.length; i++) {
            Producto producto = this.clienteService.findProductoById(itemId[i]);
            ItemFactura linea = new ItemFactura();
            linea.setCantidad(cantidad[i]);
            linea.setProducto(producto);
            factura.addItemFactura(linea);
        }
        log.info("Agregamos a factura " + itemId.length + " lineas ");
        this.clienteService.saveFactura(factura);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("success", "Factura creada con éxito");
        return "redirect:/ver/" + factura.getCliente().getId();
    }

    @GetMapping("/ver/{id_factura}")
    public String ver(@PathVariable("id_factura") Long id, Model model, RedirectAttributes redirectAttributes) {
        Factura factura = this.clienteService.findFacturaById(id);
        if (factura == null) {
            redirectAttributes.addFlashAttribute("danger", "La factura no existe");
            return "redirect:/listar";
        }
        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Ver Factura " + factura.getId().toString());
        return "factura/ver";
    }

}
