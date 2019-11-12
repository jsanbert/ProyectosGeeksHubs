package com.geekshubs.ejemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/blog")
public class IndexController {

    @GetMapping(value = { "/index", "/home", "" })
    public String index(Model model) {
        model.addAttribute("titulo", "Página sobre Spring");
        return "index";
    }
}