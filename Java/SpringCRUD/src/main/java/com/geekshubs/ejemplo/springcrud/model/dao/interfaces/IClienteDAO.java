package com.geekshubs.ejemplo.springcrud.model.dao.interfaces;

import com.geekshubs.ejemplo.springcrud.model.entities.Cliente;

import java.util.List;

public interface IClienteDAO {

    Cliente findClientById(Long id);
    // -----
    void insertOrUpdateClient(Cliente c);
    void removeClientById(Long id);
    // -----
    List<Cliente> findAllClients();
    List<Cliente> findAllClientsWithName(String name);
}