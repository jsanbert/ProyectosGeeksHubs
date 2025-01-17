package com.geekshubs.ejemplo.springcdi.controller;

import com.geekshubs.ejemplo.springcdi.model.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springcdi")
public class UsoServiceController {

    private IServicio miServicio;

    public UsoServiceController(@Qualifier("MiServicio") IServicio servicio) {
        miServicio = servicio;
    }

    @GetMapping("/usoMiServicio")
    public String index(Model model) {
        model.addAttribute("msg", miServicio.operacion());
        return "springcdi/index";
    }
}