package com.geekshubs.facturas.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Factura {
    private String descripcion;
    private Cliente cliente;
    private List<ItemFactura> listaItemsFactura;

    @Autowired
    public Factura(@Qualifier("clienteAleatorio") Cliente cliente, @Qualifier("itemsOficina") List<ItemFactura> listaItemsFactura) {
        this.cliente = cliente;
        this.listaItemsFactura = listaItemsFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getListaItemsFactura() {
        return listaItemsFactura;
    }

    public void setListaItemsFactura(List<ItemFactura> listaItemsFactura) {
        this.listaItemsFactura = listaItemsFactura;
    }
}
