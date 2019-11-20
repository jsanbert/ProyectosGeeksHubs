package com.geekshubs.minifactura.controller;

import com.geekshubs.minifactura.entity.Cliente;
import com.geekshubs.minifactura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{demo.minifactura.version}")
public class FacturaController {

    @Autowired
    FacturaRepository facturaR;

}