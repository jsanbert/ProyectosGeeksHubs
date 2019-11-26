package com.geekshubs.minifactura.controller;

import com.geekshubs.minifactura.entity.Factura;
import com.geekshubs.minifactura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ControllerAdvice
@RestController
@RequestMapping("/${minifactura.version}")
public class FacturaController {

    @Autowired
    FacturaRepository facturaR;

    @GetMapping("/factura/listar")
    public List<Factura> listarFacturas() {
        return facturaR.findAll();
    }
}
