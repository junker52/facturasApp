package com.learning.facturas.app.controllers;

import com.learning.facturas.app.models.Cliente;
import com.learning.facturas.app.services.ClienteService;
import com.learning.facturas.app.services.UploadPictureService;
import com.learning.facturas.app.utils.PaginatorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;


/**
 * Created by Ricard on 04/07/2018.
 */
@Controller
@SessionAttributes("cliente")
public class ClienteController {

    private final Path UPLOAD_IMAGES_PATH = Paths.get("uploads");

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UploadPictureService uploadPictureService;


    @RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "1") int pageNumber, Model model, Authentication authentication) {

        if (!ObjectUtils.isEmpty(authentication)) {
            this.log.info(String.format("El usuario %s ha iniciado sesion", authentication.getName()));
        }
        if (this.hasRole("ROLE_ADMIN")) {
            this.log.info("Acceso de ADMIN");
        }

        //Obtener el Authentication en cualquier punto de la aplicación
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("titulo","Listado de Clientes");
        Page<Cliente> page = this.clienteService.getPage(pageNumber);
        PaginatorHelper paginatorHelper = new PaginatorHelper(page);
        model.addAttribute("clientesList",page.getContent());
        model.addAttribute("paginator", paginatorHelper);
        return "listar";
    }

    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String ver(@PathVariable(value = "id", required = true) Long id, Model model, RedirectAttributes redirectAttributes){
        Cliente cliente = this.clienteService.fetchByIdWithFacturas(id);
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

        this.uploadPictureService.delete(cliente);
        this.uploadPictureService.loadPicture(cliente, foto);

        if (cliente.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Modificado con Exito");
        } else {
            redirectAttributes.addFlashAttribute("success", "Creado con Exito");
        }
        this.clienteService.save(cliente);
        sessionStatus.setComplete();
        return "redirect:/listar";
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
            Cliente cliente = this.clienteService.deleteOne(id);
            this.uploadPictureService.delete(cliente);
        }
        redirectAttributes.addFlashAttribute("success", "Cliente eliminado con exito");
        return "redirect:/listar";
    }

    private boolean hasRole(String roleName) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return false;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        /*
        for ( GrantedAuthority authority : authorities){
            if (roleName.equalsIgnoreCase(authority.getAuthority())){
                return true;
            }
        }
        return false;
        */
        return authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
