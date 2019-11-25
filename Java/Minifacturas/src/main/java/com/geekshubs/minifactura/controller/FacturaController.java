package com.geekshubs.minifactura.controller;

import com.geekshubs.minifactura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacturaController {

    @Autowired
    FacturaRepository facturaR;
}
