package com.geekshubs.minifactura.repository;

import com.geekshubs.minifactura.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
}
