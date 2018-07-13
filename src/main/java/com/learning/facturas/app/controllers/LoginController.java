package com.learning.facturas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Created by Ricard on 13/07/2018.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (principal != null) {
            redirectAttributes.addFlashAttribute("success", "Sessión ya iniciada");
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("danger", "Login incorrecto");
        }
        return "login";
    }
}
