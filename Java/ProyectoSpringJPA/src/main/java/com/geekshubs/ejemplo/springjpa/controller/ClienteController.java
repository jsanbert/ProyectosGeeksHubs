package com.geekshubs.ejemplo.springjpa.controller;

import com.geekshubs.ejemplo.springjpa.model.dao.interfaces.IClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteDAO clienteDAO;

    @GetMapping("/all")
    public String listAllClients(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("listaClientes", clienteDAO.findAll());
        return "lista_clientes";
    }
}
