package com.geekshubs.ejemplo.springjpa.model.dao.interfaces;

import com.geekshubs.ejemplo.springjpa.model.entities.Cliente;

import java.util.List;

public interface IClienteDAO {

    Cliente findById(String id);
    List<Cliente> findAll();
    List<Cliente> findAllClientsWithName(String name);
}
