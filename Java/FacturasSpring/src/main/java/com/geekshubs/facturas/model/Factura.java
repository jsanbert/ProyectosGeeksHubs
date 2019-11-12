package com.geekshubs.facturas.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Factura")
public class Factura {
    private String descripcion;
    private Cliente cliente;
    private List<ItemFactura> listaItemsFactura;

    @Autowired
    public Factura(@Qualifier("descripcionFactura") String descripcion, @Qualifier("clienteAleatorio") Cliente cliente, @Qualifier("itemsOficina") List<ItemFactura> listaItemsFactura) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.listaItemsFactura = listaItemsFactura;
    }

    @Bean(name="descripcionFactura")
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

    @Bean(name="listaItemsFactura")
    public List<ItemFactura> getListaItemsFactura() {
        return listaItemsFactura;
    }

    public void setListaItemsFactura(List<ItemFactura> listaItemsFactura) {
        this.listaItemsFactura = listaItemsFactura;
    }
}
