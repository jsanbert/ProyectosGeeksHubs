package com.geekshubs.ejemplo.springbasico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/springbasico/param")
public class ParamsController {

    @GetMapping({"/string"})
    public String paramString(@RequestParam(defaultValue = "") String param, Model model) {
        model.addAttribute("titulo", "Param controller");
        model.addAttribute("paramText", (param.trim().isEmpty()) ? "<ninguno>" : param);
        return "springbasico/param/index";
    }
}
