package com.geekshubs.minifactura.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geekshubs.minifactura.entity.Cliente;
import com.geekshubs.minifactura.entity.ClienteSimplificado;
import com.geekshubs.minifactura.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository <Cliente,Long> {

    @Query("SELECT c FROM Cliente c")
    List<ClienteSimplificado> findClienteSimplificado();

}
