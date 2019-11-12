package com.geekshubs.ejemplo.springcdi.controller;

import com.geekshubs.ejemplo.springcdi.model.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springcdi")
public class UsoServiceNuevoController {


    private IServicio miServicio;

    public UsoServiceNuevoController(@Qualifier("MiServicioNuevo") IServicio servicio) {
        miServicio = servicio;
    }

    @GetMapping("/usoMiServicioNuevo")
    public String index(Model model) {
        model.addAttribute("msg", miServicio.operacion());
        return "springcdi/index";
    }
}