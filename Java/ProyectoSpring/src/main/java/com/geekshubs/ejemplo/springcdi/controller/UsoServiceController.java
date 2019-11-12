package com.geekshubs.ejemplo.springcdi.controller;

import com.geekshubs.ejemplo.springcdi.model.service.MiServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsoServiceController {
    private MiServicio miServicio = new MiServicio();
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", miServicio.operacion());
        return "index";
    }
}