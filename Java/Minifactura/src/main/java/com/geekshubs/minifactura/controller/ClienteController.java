package com.geekshubs.minifactura.controller;

import com.geekshubs.minifactura.entity.Cliente;
import com.geekshubs.minifactura.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{demo.minifactura.version}")
public class ClienteController {

    @Autowired
    ClienteRepository clienteR;

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        System.out.println("*** Petición listado clientes ***");
        return clienteR.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Cliente detalle(@PathVariable String id) {
        System.out.println("*** Petición detalle cliente ***");
        return clienteR.findById(Long.parseLong(id)).orElse(null);
    }

}