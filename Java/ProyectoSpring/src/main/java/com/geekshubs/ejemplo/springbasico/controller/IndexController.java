package com.geekshubs.ejemplo.springbasico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/springbasico")
public class IndexController {

    @GetMapping(value = { "/index", "" })
    public String index(Model model) {
        model.addAttribute("titulo", "Página sobre Spring");
        return "springbasico/index";
    }
}