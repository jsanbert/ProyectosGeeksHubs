package com.geekshubs.facturas.model.service;

import com.geekshubs.facturas.model.Cliente;
import com.geekshubs.facturas.model.ItemFactura;

import java.util.List;

public interface IFacturasService {
    List<ItemFactura> registrarItemsOficina();
    Cliente generarClienteAleatorio();
}
