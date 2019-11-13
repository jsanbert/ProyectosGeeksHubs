package com.geekshubs.ejemplo.springjpa.model.dao.interfaces;

import com.geekshubs.ejemplo.springjpa.model.entities.Cliente;

import java.util.List;

public interface IClienteDAO {

    List<Cliente> findAll();

}
