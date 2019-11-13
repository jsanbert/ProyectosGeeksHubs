package com.geekshubs.facturas.controller;

import com.geekshubs.facturas.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private Factura factura;

    @GetMapping("/show")
    public String listarItemsFactura(Model model) {
        model.addAttribute("titulo", "Lista de facturas");
        model.addAttribute("factura", factura);
        return "lista_items_factura";
    }
}
