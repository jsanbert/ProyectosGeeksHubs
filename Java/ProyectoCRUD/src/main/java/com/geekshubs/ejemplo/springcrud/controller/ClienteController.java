package com.geekshubs.ejemplo.springcrud.controller;

import com.geekshubs.ejemplo.springcrud.model.dao.interfaces.IClienteDAO;
import com.geekshubs.ejemplo.springcrud.model.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteDAO clienteDAO;

    @GetMapping("/all")
    public String listAllClients(@RequestParam(defaultValue="") String name, Model model) {
        List<Cliente> resultado;
        if(name.isEmpty())
            resultado = clienteDAO.findAllClients();
        else
            resultado = clienteDAO.findAllClientsWithName(name);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("listaClientes", resultado);
        return "lista_clientes";
    }

    @GetMapping("/details/{id}")
    public String clientDetail(@PathVariable Long id, Model model) {
        Cliente cliente = clienteDAO.findClientById(id);

        if(cliente == null)
            return "nuevo_cliente";

        model.addAttribute("titulo", "Detalle cliente");
        model.addAttribute("cliente", cliente);
        return "detalle_cliente";
    }

    @GetMapping("/new")
    public String newClientForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("titulo", "Nuevo cliente");
        model.addAttribute("cliente", cliente);
        return "nuevo_cliente";
    }

    @PostMapping("/new")
    public String saveClient(@Valid Cliente cliente, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo cliente");
            return "nuevo_cliente";
        }

        clienteDAO.insertOrUpdateClient(cliente);
        return "redirect:/clientes/all";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        if(id > 0) {
            Cliente cliente = clienteDAO.findClientById(id);
            model.addAttribute("cliente", cliente);
            model.addAttribute("titulo", "Editar Cliente");
            return "nuevo_cliente";
        } else
            return "redirect:/clientes/all";
    }

    @GetMapping("/remove/{id}")
    public String removeClient(@PathVariable Long id, Model model) {
        if(id > 0)
            clienteDAO.removeClientById(id);

        return "redirect:/clientes/all";
    }
}
