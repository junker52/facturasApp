package com.learning.facturas.app.controllers;

import com.learning.facturas.app.com.learning.facturas.app.services.ClienteService;
import com.learning.facturas.app.com.learning.facturas.app.utils.PaginatorHelper;
import com.learning.facturas.app.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


/**
 * Created by Ricard on 04/07/2018.
 */
@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "1") int pageNumber, Model model){
        model.addAttribute("titulo","Listado de Clientes");
        Page<Cliente> page = this.clienteService.getPage(pageNumber);
        PaginatorHelper paginatorHelper = new PaginatorHelper(page);
        model.addAttribute("clientesList",page.getContent());
        model.addAttribute("paginator", paginatorHelper);
        return "listar";
    }

    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String ver(@PathVariable(value = "id", required = true) Long id, Model model, RedirectAttributes redirectAttributes){
        Cliente cliente = this.clienteService.findOne(id);
        if (cliente == null){
            redirectAttributes.addFlashAttribute("danger", "No se encuentra el cliente");
            return "redirect:/listar";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Detalle del cliente "+cliente.getId().intValue());
        return "ver";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){
        model.put("titulo","Formulario de cliente");
        Cliente cliente = new Cliente();
        model.put("cliente",cliente);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult,
                          Model model, SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes, @RequestParam("file")MultipartFile foto){
        if (bindingResult.hasErrors()){
            model.addAttribute("titulo","Formulario de cliente");
            return "form";
        }

        this.guardarFoto(foto,cliente);

        if (cliente.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Modificado con Exito");
        } else {
            redirectAttributes.addFlashAttribute("success", "Creado con Exito");
        }
        this.clienteService.save(cliente);
        sessionStatus.setComplete();
        return "redirect:/listar";
    }

    private void guardarFoto(MultipartFile foto, Cliente cliente){
        if (!foto.isEmpty()){
            Path recursos = Paths.get("src//main//resources//static//uploads");
            String rootPath = recursos.toFile().getAbsolutePath();
            try {
                byte[] imageBytes = foto.getBytes();
                Path completePath = Paths.get(rootPath+"//"+foto.getOriginalFilename());
                Files.write(completePath,imageBytes);
                cliente.setFoto(foto.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes redirectAttributes){
        Cliente cliente = null;
        if (id > 0){
            cliente = this.clienteService.findOne(id);
        } else {
            redirectAttributes.addFlashAttribute("danger", "Id de cliente incorrecto");
            model.addAttribute("titulo","Listado de Clientes");
            return "redirect:/listar";
        }
        model.addAttribute("cliente",cliente);
        model.addAttribute("titulo","Formulario de cliente");
        return "form";
    }

    @RequestMapping(value = "/delete/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes){
        if (id != null){
            this.clienteService.deleteOne(id);
        }
        redirectAttributes.addFlashAttribute("success", "Cliente eliminado con exito");
        return "redirect:/listar";
    }
}
