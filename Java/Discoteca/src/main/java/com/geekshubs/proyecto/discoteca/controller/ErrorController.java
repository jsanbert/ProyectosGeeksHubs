package com.geekshubs.proyecto.discoteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/error")
public class ErrorController {
    @GetMapping("")
    public String showErrorPage(@RequestParam String errorTitle, @RequestParam String errorMessage, Model model) {
        model.addAttribute("title", "Error");
        model.addAttribute("errorTitle", errorTitle);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
