package com.geekshubs.ejemplo.springjpa.controller;

import com.geekshubs.ejemplo.springjpa.model.dao.interfaces.IClienteDAO;
import com.geekshubs.ejemplo.springjpa.model.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteDAO clienteDAO;

    @GetMapping("/all")
    public String listAllClients(@RequestParam(name="name", defaultValue="") String name, Model model) {
        List<Cliente> resultado;
        if(name.isEmpty())
            resultado = clienteDAO.findAll();
        else
            resultado = clienteDAO.findAllClientsWithName(name);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("listaClientes", resultado);
        return "lista_clientes";
    }

    @GetMapping("/id/{id}")
    public String clientDetail(@PathVariable String id, Model model) {
        model.addAttribute("titulo", "Detalle cliente");
        model.addAttribute("cliente", clienteDAO.findById(id));
        return "detalle_cliente";
    }
}
