package com.geekshubs.minifactura.repository;

import com.geekshubs.minifactura.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
